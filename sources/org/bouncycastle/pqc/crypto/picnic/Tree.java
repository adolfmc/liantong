package org.bouncycastle.pqc.crypto.picnic;

import java.lang.reflect.Array;
import java.util.logging.Logger;
import org.bouncycastle.crypto.Xof;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Tree {
    private static final Logger LOG = Logger.getLogger(Tree.class.getName());
    private static final int MAX_SEED_SIZE_BYTES = 32;
    private final int MAX_AUX_BYTES = 176;
    private int dataSize;
    private int depth;
    private PicnicEngine engine;
    private boolean[] exists;
    private boolean[] haveNode;
    byte[][] nodes;
    private int numLeaves;
    private int numNodes;

    public Tree(PicnicEngine picnicEngine, int i, int i2) {
        int i3;
        this.engine = picnicEngine;
        this.depth = Utils.ceil_log2(i) + 1;
        int i4 = this.depth;
        this.numNodes = ((1 << i4) - 1) - ((1 << (i4 - 1)) - i);
        this.numLeaves = i;
        this.dataSize = i2;
        this.nodes = (byte[][]) Array.newInstance(byte.class, this.numNodes, i2);
        int i5 = 0;
        while (true) {
            i3 = this.numNodes;
            if (i5 >= i3) {
                break;
            }
            this.nodes[i5] = new byte[i2];
            i5++;
        }
        this.haveNode = new boolean[i3];
        this.exists = new boolean[i3];
        Arrays.fill(this.exists, i3 - this.numLeaves, i3, true);
        for (int i6 = this.numNodes - this.numLeaves; i6 > 0; i6--) {
            int i7 = i6 * 2;
            if (exists(i7 + 1) || exists(i7 + 2)) {
                this.exists[i6] = true;
            }
        }
        this.exists[0] = true;
    }

    private void computeParentHash(int i, byte[] bArr) {
        if (exists(i)) {
            int parent = getParent(i);
            boolean[] zArr = this.haveNode;
            if (zArr[parent]) {
                return;
            }
            int i2 = parent * 2;
            int i3 = i2 + 1;
            if (zArr[i3]) {
                int i4 = i2 + 2;
                if (!exists(i4) || this.haveNode[i4]) {
                    this.engine.digest.update((byte) 3);
                    this.engine.digest.update(this.nodes[i3], 0, this.engine.digestSizeBytes);
                    if (hasRightChild(parent)) {
                        this.engine.digest.update(this.nodes[i4], 0, this.engine.digestSizeBytes);
                    }
                    Xof xof = this.engine.digest;
                    PicnicEngine picnicEngine = this.engine;
                    xof.update(bArr, 0, 32);
                    this.engine.digest.update(Pack.intToLittleEndian(parent), 0, 2);
                    this.engine.digest.doFinal(this.nodes[parent], 0, this.engine.digestSizeBytes);
                    this.haveNode[parent] = true;
                }
            }
        }
    }

    private boolean contains(int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i; i3++) {
            if (iArr[i3] == i2) {
                return true;
            }
        }
        return false;
    }

    private boolean exists(int i) {
        if (i >= this.numNodes) {
            return false;
        }
        return this.exists[i];
    }

    private void expandSeeds(byte[] bArr, int i) {
        byte[] bArr2 = new byte[64];
        int parent = getParent(this.numNodes - 1);
        for (int i2 = 0; i2 <= parent; i2++) {
            if (this.haveNode[i2]) {
                hashSeed(bArr2, this.nodes[i2], bArr, (byte) 1, i, i2);
                int i3 = i2 * 2;
                int i4 = i3 + 1;
                if (!this.haveNode[i4]) {
                    System.arraycopy(bArr2, 0, this.nodes[i4], 0, this.engine.seedSizeBytes);
                    this.haveNode[i4] = true;
                }
                int i5 = i3 + 2;
                if (exists(i5) && !this.haveNode[i5]) {
                    System.arraycopy(bArr2, this.engine.seedSizeBytes, this.nodes[i5], 0, this.engine.seedSizeBytes);
                    this.haveNode[i5] = true;
                }
            }
        }
    }

    private int getParent(int i) {
        return (isLeftChild(i) ? i - 1 : i - 2) / 2;
    }

    private int[] getRevealedMerkleNodes(int[] iArr, int i, int[] iArr2) {
        int i2 = this.numNodes;
        int i3 = i2 - this.numLeaves;
        boolean[] zArr = new boolean[i2];
        for (int i4 = 0; i4 < i; i4++) {
            zArr[iArr[i4] + i3] = true;
        }
        for (int parent = getParent(this.numNodes - 1); parent > 0; parent--) {
            if (exists(parent)) {
                int i5 = parent * 2;
                int i6 = i5 + 2;
                if (exists(i6)) {
                    if (zArr[i5 + 1] && zArr[i6]) {
                        zArr[parent] = true;
                    }
                } else if (zArr[i5 + 1]) {
                    zArr[parent] = true;
                }
            }
        }
        int[] iArr3 = new int[this.numLeaves];
        int i7 = 0;
        for (int i8 = 0; i8 < i; i8++) {
            int i9 = iArr[i8] + i3;
            while (true) {
                if (zArr[getParent(i9)]) {
                    i9 = getParent(i9);
                    if (i9 == 0) {
                        break;
                    }
                } else if (!contains(iArr3, i7, i9)) {
                    iArr3[i7] = i9;
                    i7++;
                }
            }
        }
        iArr2[0] = i7;
        return iArr3;
    }

    private int[] getRevealedNodes(int[] iArr, int i, int[] iArr2) {
        int i2 = this.depth - 1;
        int[][] iArr3 = (int[][]) Array.newInstance(int.class, i2, i);
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = iArr[i3] + (this.numNodes - this.numLeaves);
            iArr3[0][i3] = i4;
            int i5 = 1;
            while (true) {
                i4 = getParent(i4);
                if (i4 != 0) {
                    iArr3[i5][i3] = i4;
                    i5++;
                }
            }
        }
        int[] iArr4 = new int[this.numLeaves];
        int i6 = 0;
        int i7 = 0;
        while (i6 < i2) {
            int i8 = i7;
            for (int i9 = 0; i9 < i; i9++) {
                if (hasSibling(iArr3[i6][i9])) {
                    int sibling = getSibling(iArr3[i6][i9]);
                    if (!contains(iArr3[i6], i, sibling)) {
                        while (!hasRightChild(sibling) && !isLeafNode(sibling)) {
                            sibling = (sibling * 2) + 1;
                        }
                        if (!contains(iArr4, i8, sibling)) {
                            iArr4[i8] = sibling;
                            i8++;
                        }
                    }
                }
            }
            i6++;
            i7 = i8;
        }
        iArr2[0] = i7;
        return iArr4;
    }

    private int getSibling(int i) {
        if (isLeftChild(i)) {
            int i2 = i + 1;
            if (i2 < this.numNodes) {
                return i2;
            }
            LOG.fine("getSibling: request for node with not sibling");
            return 0;
        }
        return i - 1;
    }

    private boolean hasRightChild(int i) {
        return (i * 2) + 2 < this.numNodes && exists(i);
    }

    private boolean hasSibling(int i) {
        if (exists(i)) {
            return !isLeftChild(i) || exists(i + 1);
        }
        return false;
    }

    private void hashSeed(byte[] bArr, byte[] bArr2, byte[] bArr3, byte b, int i, int i2) {
        this.engine.digest.update(b);
        this.engine.digest.update(bArr2, 0, this.engine.seedSizeBytes);
        Xof xof = this.engine.digest;
        PicnicEngine picnicEngine = this.engine;
        xof.update(bArr3, 0, 32);
        this.engine.digest.update(Pack.shortToLittleEndian((short) (i & 65535)), 0, 2);
        this.engine.digest.update(Pack.shortToLittleEndian((short) (65535 & i2)), 0, 2);
        this.engine.digest.doFinal(bArr, 0, this.engine.seedSizeBytes * 2);
    }

    private boolean isLeafNode(int i) {
        return (i * 2) + 1 >= this.numNodes;
    }

    private boolean isLeftChild(int i) {
        return i % 2 == 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int addMerkleNodes(int[] iArr, int i, byte[] bArr, int i2) {
        int[] iArr2 = {0};
        int[] revealedMerkleNodes = getRevealedMerkleNodes(iArr, i, iArr2);
        for (int i3 = 0; i3 < iArr2[0]; i3++) {
            int i4 = this.dataSize;
            i2 -= i4;
            if (i2 < 0) {
                return -1;
            }
            System.arraycopy(bArr, i3 * i4, this.nodes[revealedMerkleNodes[i3]], 0, i4);
            this.haveNode[revealedMerkleNodes[i3]] = true;
        }
        return i2 != 0 ? -1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void buildMerkleTree(byte[][] bArr, byte[] bArr2) {
        int i = this.numNodes - this.numLeaves;
        for (int i2 = 0; i2 < this.numLeaves; i2++) {
            if (bArr[i2] != null) {
                int i3 = i + i2;
                System.arraycopy(bArr[i2], 0, this.nodes[i3], 0, this.dataSize);
                this.haveNode[i3] = true;
            }
        }
        for (int i4 = this.numNodes; i4 > 0; i4--) {
            computeParentHash(i4, bArr2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void generateSeeds(byte[] bArr, byte[] bArr2, int i) {
        this.nodes[0] = bArr;
        this.haveNode[0] = true;
        expandSeeds(bArr2, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[] getLeaf(int i) {
        return this.nodes[(this.numNodes - this.numLeaves) + i];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[][] getLeaves() {
        return this.nodes;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getLeavesOffset() {
        return this.numNodes - this.numLeaves;
    }

    boolean hasLeftChild(Tree tree, int i) {
        return (i * 2) + 1 < this.numNodes;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[] openMerkleTree(int[] iArr, int i, int[] iArr2) {
        int[] iArr3 = new int[1];
        int[] revealedMerkleNodes = getRevealedMerkleNodes(iArr, i, iArr3);
        iArr2[0] = iArr3[0] * this.dataSize;
        byte[] bArr = new byte[iArr2[0]];
        for (int i2 = 0; i2 < iArr3[0]; i2++) {
            byte[] bArr2 = this.nodes[revealedMerkleNodes[i2]];
            int i3 = this.dataSize;
            System.arraycopy(bArr2, 0, bArr, i2 * i3, i3);
        }
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int openMerkleTreeSize(int[] iArr, int i) {
        int[] iArr2 = new int[1];
        getRevealedMerkleNodes(iArr, i, iArr2);
        return iArr2[0] * this.engine.digestSizeBytes;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int reconstructSeeds(int[] iArr, int i, byte[] bArr, int i2, byte[] bArr2, int i3) {
        int[] iArr2 = {0};
        int[] revealedNodes = getRevealedNodes(iArr, i, iArr2);
        for (int i4 = 0; i4 < iArr2[0]; i4++) {
            i2 -= this.engine.seedSizeBytes;
            if (i2 < 0) {
                return -1;
            }
            System.arraycopy(bArr, this.engine.seedSizeBytes * i4, this.nodes[revealedNodes[i4]], 0, this.engine.seedSizeBytes);
            this.haveNode[revealedNodes[i4]] = true;
        }
        expandSeeds(bArr2, i3);
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int revealSeeds(int[] iArr, int i, byte[] bArr, int i2) {
        int[] iArr2 = {0};
        int[] revealedNodes = getRevealedNodes(iArr, i, iArr2);
        for (int i3 = 0; i3 < iArr2[0]; i3++) {
            i2 -= this.engine.seedSizeBytes;
            if (i2 < 0) {
                LOG.fine("Insufficient sized buffer provided to revealSeeds");
                return 0;
            }
            System.arraycopy(this.nodes[revealedNodes[i3]], 0, bArr, this.engine.seedSizeBytes * i3, this.engine.seedSizeBytes);
        }
        return bArr.length - i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int revealSeedsSize(int[] iArr, int i) {
        int[] iArr2 = {0};
        getRevealedNodes(iArr, i, iArr2);
        return iArr2[0] * this.engine.seedSizeBytes;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int verifyMerkleTree(byte[][] bArr, byte[] bArr2) {
        int i = this.numNodes - this.numLeaves;
        for (int i2 = 0; i2 < this.numLeaves; i2++) {
            if (bArr[i2] != null) {
                int i3 = i + i2;
                if (this.haveNode[i3]) {
                    return -1;
                }
                if (bArr[i2] != null) {
                    System.arraycopy(bArr[i2], 0, this.nodes[i3], 0, this.dataSize);
                    this.haveNode[i3] = true;
                }
            }
        }
        for (int i4 = this.numNodes; i4 > 0; i4--) {
            computeParentHash(i4, bArr2);
        }
        return !this.haveNode[0] ? -1 : 0;
    }
}
