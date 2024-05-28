package szcom.googlecode.mp4parser;

import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.lang.NoAspectBoundException;
import szorg.mp4parser.aspectj.lang.annotation.Aspect;
import szorg.mp4parser.aspectj.lang.annotation.Before;

@Aspect
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class RequiresParseDetailAspect {
    private static Throwable ajc$initFailureCause;
    public static final RequiresParseDetailAspect ajc$perSingletonInstance = null;

    static {
        try {
            ajc$postClinit();
        } catch (Throwable th) {
            ajc$initFailureCause = th;
        }
    }

    private static void ajc$postClinit() {
        ajc$perSingletonInstance = new RequiresParseDetailAspect();
    }

    public static RequiresParseDetailAspect aspectOf() {
        RequiresParseDetailAspect requiresParseDetailAspect = ajc$perSingletonInstance;
        if (requiresParseDetailAspect != null) {
            return requiresParseDetailAspect;
        }
        throw new NoAspectBoundException("szcom.googlecode.mp4parser.RequiresParseDetailAspect", ajc$initFailureCause);
    }

    public static boolean hasAspect() {
        return ajc$perSingletonInstance != null;
    }

    @Before("this(com.googlecode.mp4parser.AbstractBox) && ((execution(public * * (..)) && !( execution(* parseDetails()) || execution(* getNumOfBytesToFirstChild()) || execution(* getType()) || execution(* isParsed()) || execution(* getHeader(*)) || execution(* parse()) || execution(* getBox(*)) || execution(* getSize()) || execution(* getOffset()) || execution(* parseDetails()) || execution(* _parseDetails(*)) || execution(* parse(*,*,*,*)) || execution(* getIsoFile()) || execution(* getParent()) || execution(* setParent(*)) || execution(* getUserType()) || execution(* setUserType(*))) && !@annotation(com.googlecode.mp4parser.annotations.DoNotParseDetail)) || @annotation(com.googlecode.mp4parser.annotations.ParseDetail))")
    public void before(JoinPoint joinPoint) {
        if (joinPoint.getTarget() instanceof AbstractBox) {
            if (((AbstractBox) joinPoint.getTarget()).isParsed()) {
                return;
            }
            ((AbstractBox) joinPoint.getTarget()).parseDetails();
            return;
        }
        throw new RuntimeException("Only methods in subclasses of " + AbstractBox.class.getName() + " can  be annotated with ParseDetail");
    }
}
