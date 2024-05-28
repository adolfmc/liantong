package szcom.googlecode.mp4parser.util;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import szcom.coremedia.iso.boxes.Box;
import szcom.coremedia.iso.boxes.Container;
import szcom.googlecode.mp4parser.AbstractContainerBox;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Path {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static Pattern component = Pattern.compile("(....|\\.\\.)(\\[(.*)\\])?");

    private Path() {
    }

    public static String createPath(Box box) {
        return createPath(box, "");
    }

    private static String createPath(Box box, String str) {
        Container parent = box.getParent();
        int i = 0;
        for (Box box2 : parent.getBoxes()) {
            if (box2.getType().equals(box.getType())) {
                if (box2 == box) {
                    break;
                }
                i++;
            }
        }
        String str2 = String.valueOf(String.format("/%s[%d]", box.getType(), Integer.valueOf(i))) + str;
        return parent instanceof Box ? createPath((Box) parent, str2) : str2;
    }

    public static <T extends Box> T getPath(Box box, String str) {
        List paths = getPaths(box, str, true);
        if (paths.isEmpty()) {
            return null;
        }
        return (T) paths.get(0);
    }

    public static <T extends Box> T getPath(Container container, String str) {
        List paths = getPaths(container, str, true);
        if (paths.isEmpty()) {
            return null;
        }
        return (T) paths.get(0);
    }

    public static <T extends Box> T getPath(AbstractContainerBox abstractContainerBox, String str) {
        List paths = getPaths(abstractContainerBox, str, true);
        if (paths.isEmpty()) {
            return null;
        }
        return (T) paths.get(0);
    }

    private static <T extends Box> List<T> getPaths(Object obj, String str, boolean z) {
        String str2;
        if (str.startsWith("/")) {
            String substring = str.substring(1);
            while (obj instanceof Box) {
                obj = ((Box) obj).getParent();
            }
            str = substring;
        }
        if (str.length() == 0) {
            if (obj instanceof Box) {
                return Collections.singletonList((Box) obj);
            }
            throw new RuntimeException("Result of path expression seems to be the root container. This is not allowed!");
        }
        int i = 0;
        if (str.contains("/")) {
            str2 = str.substring(str.indexOf(47) + 1);
            str = str.substring(0, str.indexOf(47));
        } else {
            str2 = "";
        }
        Matcher matcher = component.matcher(str);
        if (!matcher.matches()) {
            throw new RuntimeException(String.valueOf(str) + " is invalid path.");
        }
        String group2 = matcher.group(1);
        if ("..".equals(group2)) {
            return obj instanceof Box ? getPaths(((Box) obj).getParent(), str2, z) : Collections.emptyList();
        } else if (obj instanceof Container) {
            int parseInt = matcher.group(2) != null ? Integer.parseInt(matcher.group(3)) : -1;
            LinkedList linkedList = new LinkedList();
            for (Box box : ((Container) obj).getBoxes()) {
                if (box.getType().matches(group2)) {
                    if (parseInt == -1 || parseInt == i) {
                        linkedList.addAll(getPaths(box, str2, z));
                    }
                    i++;
                }
                if (z || parseInt >= 0) {
                    if (!linkedList.isEmpty()) {
                        return linkedList;
                    }
                }
            }
            return linkedList;
        } else {
            return Collections.emptyList();
        }
    }

    public static <T extends Box> List<T> getPaths(Box box, String str) {
        return getPaths(box, str, false);
    }

    private static <T extends Box> List<T> getPaths(Box box, String str, boolean z) {
        return getPaths((Object) box, str, z);
    }

    public static <T extends Box> List<T> getPaths(Container container, String str) {
        return getPaths(container, str, false);
    }

    private static <T extends Box> List<T> getPaths(Container container, String str, boolean z) {
        return getPaths((Object) container, str, z);
    }

    private static <T extends Box> List<T> getPaths(AbstractContainerBox abstractContainerBox, String str, boolean z) {
        return getPaths((Object) abstractContainerBox, str, z);
    }

    public static boolean isContained(Box box, String str) {
        return getPaths(box, str).contains(box);
    }
}
