package io.objectbox;

import com.google.flatbuffers.FlatBufferBuilder;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.model.IdUid;
import io.objectbox.model.Model;
import io.objectbox.model.ModelEntity;
import io.objectbox.model.ModelProperty;
import io.objectbox.model.ModelRelation;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

@Internal
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class ModelBuilder {
    private static final int MODEL_VERSION = 2;
    Integer lastEntityId;
    Long lastEntityUid;
    Integer lastIndexId;
    Long lastIndexUid;
    Integer lastRelationId;
    Long lastRelationUid;
    final FlatBufferBuilder fbb = new FlatBufferBuilder();
    final List<Integer> entityOffsets = new ArrayList();
    long version = 1;

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class PropertyBuilder {
        boolean finished;
        private int flags;

        /* renamed from: id */
        private int f24388id;
        private int indexId;
        private int indexMaxValueLength;
        private long indexUid;
        private final int propertyNameOffset;
        private int secondaryNameOffset;
        private final int targetEntityOffset;
        private final int type;
        private long uid;
        private final int virtualTargetOffset;

        PropertyBuilder(String str, String str2, @Nullable String str3, @Nullable int i) {
            this.type = i;
            this.propertyNameOffset = ModelBuilder.this.fbb.createString(str);
            this.targetEntityOffset = str2 != null ? ModelBuilder.this.fbb.createString(str2) : 0;
            this.virtualTargetOffset = str3 != null ? ModelBuilder.this.fbb.createString(str3) : 0;
        }

        /* renamed from: id */
        public PropertyBuilder m1966id(int i, long j) {
            checkNotFinished();
            this.f24388id = i;
            this.uid = j;
            return this;
        }

        public PropertyBuilder indexId(int i, long j) {
            checkNotFinished();
            this.indexId = i;
            this.indexUid = j;
            return this;
        }

        public PropertyBuilder indexMaxValueLength(int i) {
            checkNotFinished();
            this.indexMaxValueLength = i;
            return this;
        }

        public PropertyBuilder flags(int i) {
            checkNotFinished();
            this.flags = i;
            return this;
        }

        public PropertyBuilder secondaryName(String str) {
            checkNotFinished();
            this.secondaryNameOffset = ModelBuilder.this.fbb.createString(str);
            return this;
        }

        private void checkNotFinished() {
            if (this.finished) {
                throw new IllegalStateException("Already finished");
            }
        }

        public int finish() {
            checkNotFinished();
            this.finished = true;
            ModelProperty.startModelProperty(ModelBuilder.this.fbb);
            ModelProperty.addName(ModelBuilder.this.fbb, this.propertyNameOffset);
            if (this.targetEntityOffset != 0) {
                ModelProperty.addTargetEntity(ModelBuilder.this.fbb, this.targetEntityOffset);
            }
            if (this.virtualTargetOffset != 0) {
                ModelProperty.addVirtualTarget(ModelBuilder.this.fbb, this.virtualTargetOffset);
            }
            if (this.secondaryNameOffset != 0) {
                ModelProperty.addNameSecondary(ModelBuilder.this.fbb, this.secondaryNameOffset);
            }
            if (this.f24388id != 0) {
                ModelProperty.addId(ModelBuilder.this.fbb, IdUid.createIdUid(ModelBuilder.this.fbb, this.f24388id, this.uid));
            }
            if (this.indexId != 0) {
                ModelProperty.addIndexId(ModelBuilder.this.fbb, IdUid.createIdUid(ModelBuilder.this.fbb, this.indexId, this.indexUid));
            }
            if (this.indexMaxValueLength > 0) {
                ModelProperty.addMaxIndexValueLength(ModelBuilder.this.fbb, this.indexMaxValueLength);
            }
            ModelProperty.addType(ModelBuilder.this.fbb, this.type);
            if (this.flags != 0) {
                ModelProperty.addFlags(ModelBuilder.this.fbb, this.flags);
            }
            return ModelProperty.endModelProperty(ModelBuilder.this.fbb);
        }
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class EntityBuilder {
        boolean finished;
        Integer flags;

        /* renamed from: id */
        Integer f24387id;
        Integer lastPropertyId;
        Long lastPropertyUid;
        final String name;
        PropertyBuilder propertyBuilder;
        final List<Integer> propertyOffsets = new ArrayList();
        final List<Integer> relationOffsets = new ArrayList();
        Long uid;

        EntityBuilder(String str) {
            this.name = str;
        }

        /* renamed from: id */
        public EntityBuilder m1967id(int i, long j) {
            checkNotFinished();
            this.f24387id = Integer.valueOf(i);
            this.uid = Long.valueOf(j);
            return this;
        }

        public EntityBuilder lastPropertyId(int i, long j) {
            checkNotFinished();
            this.lastPropertyId = Integer.valueOf(i);
            this.lastPropertyUid = Long.valueOf(j);
            return this;
        }

        public EntityBuilder flags(int i) {
            this.flags = Integer.valueOf(i);
            return this;
        }

        private void checkNotFinished() {
            if (this.finished) {
                throw new IllegalStateException("Already finished");
            }
        }

        public PropertyBuilder property(String str, int i) {
            return property(str, null, i);
        }

        public PropertyBuilder property(String str, @Nullable String str2, int i) {
            return property(str, str2, null, i);
        }

        public PropertyBuilder property(String str, @Nullable String str2, @Nullable String str3, int i) {
            checkNotFinished();
            checkFinishProperty();
            this.propertyBuilder = new PropertyBuilder(str, str2, str3, i);
            return this.propertyBuilder;
        }

        void checkFinishProperty() {
            PropertyBuilder propertyBuilder = this.propertyBuilder;
            if (propertyBuilder != null) {
                this.propertyOffsets.add(Integer.valueOf(propertyBuilder.finish()));
                this.propertyBuilder = null;
            }
        }

        public EntityBuilder relation(String str, int i, long j, int i2, long j2) {
            checkNotFinished();
            checkFinishProperty();
            int createString = ModelBuilder.this.fbb.createString(str);
            ModelRelation.startModelRelation(ModelBuilder.this.fbb);
            ModelRelation.addName(ModelBuilder.this.fbb, createString);
            ModelRelation.addId(ModelBuilder.this.fbb, IdUid.createIdUid(ModelBuilder.this.fbb, i, j));
            ModelRelation.addTargetEntityId(ModelBuilder.this.fbb, IdUid.createIdUid(ModelBuilder.this.fbb, i2, j2));
            this.relationOffsets.add(Integer.valueOf(ModelRelation.endModelRelation(ModelBuilder.this.fbb)));
            return this;
        }

        public ModelBuilder entityDone() {
            checkNotFinished();
            checkFinishProperty();
            this.finished = true;
            int createString = ModelBuilder.this.fbb.createString(this.name);
            int createVector = ModelBuilder.this.createVector(this.propertyOffsets);
            int createVector2 = this.relationOffsets.isEmpty() ? 0 : ModelBuilder.this.createVector(this.relationOffsets);
            ModelEntity.startModelEntity(ModelBuilder.this.fbb);
            ModelEntity.addName(ModelBuilder.this.fbb, createString);
            ModelEntity.addProperties(ModelBuilder.this.fbb, createVector);
            if (createVector2 != 0) {
                ModelEntity.addRelations(ModelBuilder.this.fbb, createVector2);
            }
            if (this.f24387id != null && this.uid != null) {
                ModelEntity.addId(ModelBuilder.this.fbb, IdUid.createIdUid(ModelBuilder.this.fbb, this.f24387id.intValue(), this.uid.longValue()));
            }
            if (this.lastPropertyId != null) {
                ModelEntity.addLastPropertyId(ModelBuilder.this.fbb, IdUid.createIdUid(ModelBuilder.this.fbb, this.lastPropertyId.intValue(), this.lastPropertyUid.longValue()));
            }
            if (this.flags != null) {
                ModelEntity.addFlags(ModelBuilder.this.fbb, this.flags.intValue());
            }
            ModelBuilder.this.entityOffsets.add(Integer.valueOf(ModelEntity.endModelEntity(ModelBuilder.this.fbb)));
            return ModelBuilder.this;
        }
    }

    int createVector(List<Integer> list) {
        int[] iArr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            iArr[i] = list.get(i).intValue();
        }
        return this.fbb.createVectorOfTables(iArr);
    }

    public ModelBuilder version(long j) {
        this.version = j;
        return this;
    }

    public EntityBuilder entity(String str) {
        return new EntityBuilder(str);
    }

    public ModelBuilder lastEntityId(int i, long j) {
        this.lastEntityId = Integer.valueOf(i);
        this.lastEntityUid = Long.valueOf(j);
        return this;
    }

    public ModelBuilder lastIndexId(int i, long j) {
        this.lastIndexId = Integer.valueOf(i);
        this.lastIndexUid = Long.valueOf(j);
        return this;
    }

    public ModelBuilder lastRelationId(int i, long j) {
        this.lastRelationId = Integer.valueOf(i);
        this.lastRelationUid = Long.valueOf(j);
        return this;
    }

    public byte[] build() {
        int createString = this.fbb.createString("default");
        int createVector = createVector(this.entityOffsets);
        Model.startModel(this.fbb);
        Model.addName(this.fbb, createString);
        Model.addModelVersion(this.fbb, 2L);
        Model.addVersion(this.fbb, 1L);
        Model.addEntities(this.fbb, createVector);
        Integer num = this.lastEntityId;
        if (num != null) {
            Model.addLastEntityId(this.fbb, IdUid.createIdUid(this.fbb, num.intValue(), this.lastEntityUid.longValue()));
        }
        Integer num2 = this.lastIndexId;
        if (num2 != null) {
            Model.addLastIndexId(this.fbb, IdUid.createIdUid(this.fbb, num2.intValue(), this.lastIndexUid.longValue()));
        }
        Integer num3 = this.lastRelationId;
        if (num3 != null) {
            Model.addLastRelationId(this.fbb, IdUid.createIdUid(this.fbb, num3.intValue(), this.lastRelationUid.longValue()));
        }
        this.fbb.finish(Model.endModel(this.fbb));
        return this.fbb.sizedByteArray();
    }
}
