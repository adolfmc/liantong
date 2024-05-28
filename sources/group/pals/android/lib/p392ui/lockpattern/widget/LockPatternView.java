package group.pals.android.lib.p392ui.lockpattern.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import group.pals.android.lib.p392ui.lockpattern.C11982R;
import group.pals.android.lib.p392ui.lockpattern.util.C11988UI;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/* renamed from: group.pals.android.lib.ui.lockpattern.widget.LockPatternView */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class LockPatternView extends View {
    private static final int ASPECT_LOCK_HEIGHT = 2;
    private static final int ASPECT_LOCK_WIDTH = 1;
    private static final int ASPECT_SQUARE = 0;
    public static final int MATRIX_SIZE = 9;
    public static final int MATRIX_WIDTH = 3;
    private static final int MILLIS_PER_CIRCLE_ANIMATING = 700;
    private static final boolean PROFILE_DRAWING = false;
    static final int STATUS_BAR_HEIGHT = 25;
    private long mAnimatingPeriodStart;
    private final Matrix mArrowMatrix;
    private int mAspect;
    private Bitmap mBitmapArrowGreenUp;
    private Bitmap mBitmapArrowRedUp;
    private Bitmap mBitmapBtnDefault;
    private Bitmap mBitmapBtnTouched;
    private Bitmap mBitmapCircleDefault;
    private Bitmap mBitmapCircleGreen;
    private Bitmap mBitmapCircleRed;
    private int mBitmapHeight;
    private int mBitmapWidth;
    private final Matrix mCircleMatrix;
    private final Context mContext;
    private final Path mCurrentPath;
    private float mDiameterFactor;
    private boolean mDrawingProfilingStarted;
    private boolean mEnableHapticFeedback;
    private float mHitFactor;
    private float mInProgressX;
    private float mInProgressY;
    private boolean mInStealthMode;
    private boolean mInputEnabled;
    private final Rect mInvalidate;
    private OnPatternListener mOnPatternListener;
    private final int mPadding;
    private final int mPaddingBottom;
    private final int mPaddingLeft;
    private final int mPaddingRight;
    private final int mPaddingTop;
    private Paint mPaint;
    private Paint mPathPaint;
    private ArrayList<Cell> mPattern;
    private DisplayMode mPatternDisplayMode;
    private boolean[][] mPatternDrawLookup;
    private boolean mPatternInProgress;
    private float mSquareHeight;
    private float mSquareWidth;
    private final int mStrokeAlpha;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: group.pals.android.lib.ui.lockpattern.widget.LockPatternView$DisplayMode */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public enum DisplayMode {
        Correct,
        Animate,
        Wrong
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: group.pals.android.lib.ui.lockpattern.widget.LockPatternView$OnPatternListener */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface OnPatternListener {
        void onPatternCellAdded(List<Cell> list);

        void onPatternCleared();

        void onPatternDetected(List<Cell> list);

        void onPatternStart();
    }

    /* renamed from: group.pals.android.lib.ui.lockpattern.widget.LockPatternView$Cell */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static class Cell implements Parcelable {
        public static final Parcelable.Creator<Cell> CREATOR;
        static Cell[][] sCells = (Cell[][]) Array.newInstance(Cell.class, 3, 3);
        int mColumn;
        int mRow;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        static {
            for (int i = 0; i < 3; i++) {
                for (int i2 = 0; i2 < 3; i2++) {
                    sCells[i][i2] = new Cell(i, i2);
                }
            }
            CREATOR = new Parcelable.Creator<Cell>() { // from class: group.pals.android.lib.ui.lockpattern.widget.LockPatternView.Cell.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public Cell createFromParcel(Parcel parcel) {
                    return new Cell(parcel);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public Cell[] newArray(int i3) {
                    return new Cell[i3];
                }
            };
        }

        private Cell(int i, int i2) {
            checkRange(i, i2);
            this.mRow = i;
            this.mColumn = i2;
        }

        public int getRow() {
            return this.mRow;
        }

        public int getColumn() {
            return this.mColumn;
        }

        public int getId() {
            return (this.mRow * 3) + this.mColumn;
        }

        /* renamed from: of */
        public static synchronized Cell m2012of(int i, int i2) {
            Cell cell;
            synchronized (Cell.class) {
                checkRange(i, i2);
                cell = sCells[i][i2];
            }
            return cell;
        }

        /* renamed from: of */
        public static synchronized Cell m2013of(int i) {
            Cell m2012of;
            synchronized (Cell.class) {
                m2012of = m2012of(i / 3, i % 3);
            }
            return m2012of;
        }

        private static void checkRange(int i, int i2) {
            if (i < 0 || i > 2) {
                throw new IllegalArgumentException("row must be in range 0-2");
            }
            if (i2 < 0 || i2 > 2) {
                throw new IllegalArgumentException("column must be in range 0-2");
            }
        }

        public String toString() {
            return "(ROW=" + getRow() + ",COL=" + getColumn() + ")";
        }

        public boolean equals(Object obj) {
            if (obj instanceof Cell) {
                Cell cell = (Cell) obj;
                return getColumn() == cell.getColumn() && getRow() == cell.getRow();
            }
            return super.equals(obj);
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(getColumn());
            parcel.writeInt(getRow());
        }

        public void readFromParcel(Parcel parcel) {
            this.mColumn = parcel.readInt();
            this.mRow = parcel.readInt();
        }

        private Cell(Parcel parcel) {
            readFromParcel(parcel);
        }
    }

    public LockPatternView(Context context) {
        this(context, null);
    }

    public LockPatternView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Bitmap[] bitmapArr;
        this.mDrawingProfilingStarted = false;
        this.mPaint = new Paint();
        this.mPathPaint = new Paint();
        this.mPattern = new ArrayList<>(9);
        this.mPatternDrawLookup = (boolean[][]) Array.newInstance(boolean.class, 3, 3);
        this.mInProgressX = -1.0f;
        this.mInProgressY = -1.0f;
        this.mPatternDisplayMode = DisplayMode.Correct;
        this.mInputEnabled = true;
        this.mInStealthMode = false;
        this.mEnableHapticFeedback = true;
        this.mPatternInProgress = false;
        this.mDiameterFactor = 0.1f;
        this.mStrokeAlpha = 128;
        this.mHitFactor = 0.6f;
        this.mCurrentPath = new Path();
        this.mInvalidate = new Rect();
        this.mArrowMatrix = new Matrix();
        this.mCircleMatrix = new Matrix();
        this.mPadding = 0;
        this.mPaddingLeft = 0;
        this.mPaddingRight = 0;
        this.mPaddingTop = 0;
        this.mPaddingBottom = 0;
        this.mContext = context;
        if ("square".equals("")) {
            this.mAspect = 0;
        } else if ("lock_width".equals("")) {
            this.mAspect = 1;
        } else if ("lock_height".equals("")) {
            this.mAspect = 2;
        } else {
            this.mAspect = 0;
        }
        setClickable(true);
        this.mPathPaint.setAntiAlias(true);
        this.mPathPaint.setDither(true);
        this.mPathPaint.setColor(getResources().getColor(C11982R.C11983color.link_color));
        this.mPathPaint.setStyle(Paint.Style.STROKE);
        this.mPathPaint.setStrokeWidth(5.0f);
        this.mBitmapBtnDefault = getBitmapFor(C11988UI.resolveAttribute(getContext(), C11982R.attr.alp_drawable_btn_code_lock_default_holo));
        this.mBitmapBtnTouched = getBitmapFor(C11988UI.resolveAttribute(getContext(), C11982R.attr.alp_drawable_btn_code_lock_touched_holo));
        this.mBitmapCircleDefault = getBitmapFor(C11988UI.resolveAttribute(getContext(), C11982R.attr.alp_drawable_indicator_code_lock_point_area_default_holo));
        this.mBitmapCircleGreen = getBitmapFor(C11988UI.resolveAttribute(getContext(), C11982R.attr.aosp_drawable_indicator_code_lock_point_area_normal));
        this.mBitmapCircleRed = getBitmapFor(C11982R.C11984drawable.aosp_indicator_code_lock_point_area_red_holo);
        this.mBitmapArrowGreenUp = getBitmapFor(C11982R.C11984drawable.aosp_indicator_code_lock_drag_direction_green_up);
        this.mBitmapArrowRedUp = getBitmapFor(C11982R.C11984drawable.aosp_indicator_code_lock_drag_direction_red_up);
        for (Bitmap bitmap : new Bitmap[]{this.mBitmapBtnDefault, this.mBitmapBtnTouched, this.mBitmapCircleDefault, this.mBitmapCircleGreen, this.mBitmapCircleRed}) {
            this.mBitmapWidth = Math.max(this.mBitmapWidth, bitmap.getWidth());
            this.mBitmapHeight = Math.max(this.mBitmapHeight, bitmap.getHeight());
        }
    }

    private Bitmap getBitmapFor(int i) {
        return BitmapFactory.decodeResource(getContext().getResources(), i);
    }

    public boolean isInStealthMode() {
        return this.mInStealthMode;
    }

    public boolean isTactileFeedbackEnabled() {
        return this.mEnableHapticFeedback;
    }

    public void setInStealthMode(boolean z) {
        this.mInStealthMode = z;
    }

    public void setTactileFeedbackEnabled(boolean z) {
        this.mEnableHapticFeedback = z;
    }

    public void setOnPatternListener(OnPatternListener onPatternListener) {
        this.mOnPatternListener = onPatternListener;
    }

    public void setPattern(DisplayMode displayMode, List<Cell> list) {
        this.mPattern.clear();
        this.mPattern.addAll(list);
        clearPatternDrawLookup();
        for (Cell cell : list) {
            this.mPatternDrawLookup[cell.getRow()][cell.getColumn()] = true;
        }
        setDisplayMode(displayMode);
    }

    public void setDisplayMode(DisplayMode displayMode) {
        this.mPatternDisplayMode = displayMode;
        if (displayMode == DisplayMode.Animate) {
            if (this.mPattern.size() == 0) {
                throw new IllegalStateException("you must have a pattern to animate if you want to set the display mode to animate");
            }
            this.mAnimatingPeriodStart = SystemClock.elapsedRealtime();
            Cell cell = this.mPattern.get(0);
            this.mInProgressX = getCenterXForColumn(cell.getColumn());
            this.mInProgressY = getCenterYForRow(cell.getRow());
            clearPatternDrawLookup();
        }
        invalidate();
    }

    public DisplayMode getDisplayMode() {
        return this.mPatternDisplayMode;
    }

    public List<Cell> getPattern() {
        return (List) this.mPattern.clone();
    }

    private void notifyCellAdded() {
        OnPatternListener onPatternListener = this.mOnPatternListener;
        if (onPatternListener != null) {
            onPatternListener.onPatternCellAdded(this.mPattern);
        }
    }

    private void notifyPatternStarted() {
        OnPatternListener onPatternListener = this.mOnPatternListener;
        if (onPatternListener != null) {
            onPatternListener.onPatternStart();
        }
    }

    private void notifyPatternDetected() {
        OnPatternListener onPatternListener = this.mOnPatternListener;
        if (onPatternListener != null) {
            onPatternListener.onPatternDetected(this.mPattern);
        }
    }

    private void notifyPatternCleared() {
        OnPatternListener onPatternListener = this.mOnPatternListener;
        if (onPatternListener != null) {
            onPatternListener.onPatternCleared();
        }
    }

    public void clearPattern() {
        resetPattern();
    }

    private void resetPattern() {
        this.mPattern.clear();
        clearPatternDrawLookup();
        this.mPatternDisplayMode = DisplayMode.Correct;
        invalidate();
    }

    private void clearPatternDrawLookup() {
        for (int i = 0; i < 3; i++) {
            for (int i2 = 0; i2 < 3; i2++) {
                this.mPatternDrawLookup[i][i2] = false;
            }
        }
    }

    public void disableInput() {
        this.mInputEnabled = false;
    }

    public void enableInput() {
        this.mInputEnabled = true;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        this.mSquareWidth = ((i + 0) + 0) / 3.0f;
        this.mSquareHeight = ((i2 + 0) + 0) / 3.0f;
    }

    private int resolveMeasured(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i);
        if (mode != Integer.MIN_VALUE) {
            return mode != 0 ? size : i2;
        }
        return Math.max(size, i2);
    }

    @Override // android.view.View
    protected int getSuggestedMinimumWidth() {
        return this.mBitmapWidth * 3;
    }

    @Override // android.view.View
    protected int getSuggestedMinimumHeight() {
        return this.mBitmapWidth * 3;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int suggestedMinimumWidth = getSuggestedMinimumWidth();
        int suggestedMinimumHeight = getSuggestedMinimumHeight();
        int resolveMeasured = resolveMeasured(i, suggestedMinimumWidth);
        int resolveMeasured2 = resolveMeasured(i2, suggestedMinimumHeight);
        switch (this.mAspect) {
            case 0:
                resolveMeasured = Math.min(resolveMeasured, resolveMeasured2);
                resolveMeasured2 = resolveMeasured;
                break;
            case 1:
                resolveMeasured2 = Math.min(resolveMeasured, resolveMeasured2);
                break;
            case 2:
                resolveMeasured = Math.min(resolveMeasured, resolveMeasured2);
                break;
        }
        setMeasuredDimension(resolveMeasured, resolveMeasured2);
    }

    private Cell detectAndAddHit(float f, float f2) {
        Cell checkForNewHit = checkForNewHit(f, f2);
        Cell cell = null;
        if (checkForNewHit != null) {
            ArrayList<Cell> arrayList = this.mPattern;
            if (!arrayList.isEmpty()) {
                Cell cell2 = arrayList.get(arrayList.size() - 1);
                int i = checkForNewHit.mRow - cell2.mRow;
                int i2 = checkForNewHit.mColumn - cell2.mColumn;
                int i3 = cell2.mRow;
                int i4 = cell2.mColumn;
                if (Math.abs(i) == 2 && Math.abs(i2) != 1) {
                    i3 = cell2.mRow + (i > 0 ? 1 : -1);
                }
                if (Math.abs(i2) == 2 && Math.abs(i) != 1) {
                    i4 = cell2.mColumn + (i2 > 0 ? 1 : -1);
                }
                cell = Cell.m2012of(i3, i4);
            }
            if (cell != null && !this.mPatternDrawLookup[cell.mRow][cell.mColumn]) {
                addCellToPattern(cell);
            }
            addCellToPattern(checkForNewHit);
            if (this.mEnableHapticFeedback) {
                performHapticFeedback(1, 3);
            }
            return checkForNewHit;
        }
        return null;
    }

    private void addCellToPattern(Cell cell) {
        this.mPatternDrawLookup[cell.getRow()][cell.getColumn()] = true;
        this.mPattern.add(cell);
        notifyCellAdded();
    }

    private Cell checkForNewHit(float f, float f2) {
        int columnHit;
        int rowHit = getRowHit(f2);
        if (rowHit >= 0 && (columnHit = getColumnHit(f)) >= 0 && !this.mPatternDrawLookup[rowHit][columnHit]) {
            return Cell.m2012of(rowHit, columnHit);
        }
        return null;
    }

    private int getRowHit(float f) {
        float f2 = this.mSquareHeight;
        float f3 = this.mHitFactor * f2;
        float f4 = ((f2 - f3) / 2.0f) + 0.0f;
        for (int i = 0; i < 3; i++) {
            float f5 = (i * f2) + f4;
            if (f >= f5 && f <= f5 + f3) {
                return i;
            }
        }
        return -1;
    }

    private int getColumnHit(float f) {
        float f2 = this.mSquareWidth;
        float f3 = this.mHitFactor * f2;
        float f4 = ((f2 - f3) / 2.0f) + 0.0f;
        for (int i = 0; i < 3; i++) {
            float f5 = (i * f2) + f4;
            if (f >= f5 && f <= f5 + f3) {
                return i;
            }
        }
        return -1;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mInputEnabled && isEnabled()) {
            switch (motionEvent.getAction()) {
                case 0:
                    handleActionDown(motionEvent);
                    return true;
                case 1:
                    handleActionUp(motionEvent);
                    return true;
                case 2:
                    handleActionMove(motionEvent);
                    return true;
                case 3:
                    this.mPatternInProgress = false;
                    resetPattern();
                    notifyPatternCleared();
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }

    private void handleActionMove(MotionEvent motionEvent) {
        float x;
        float y;
        int i;
        int i2;
        float f;
        float f2;
        float f3;
        float f4;
        MotionEvent motionEvent2 = motionEvent;
        int historySize = motionEvent.getHistorySize();
        int i3 = 0;
        while (i3 < historySize + 1) {
            if (i3 < historySize) {
                x = motionEvent2.getHistoricalX(i3);
            } else {
                x = motionEvent.getX();
            }
            if (i3 < historySize) {
                y = motionEvent2.getHistoricalY(i3);
            } else {
                y = motionEvent.getY();
            }
            int size = this.mPattern.size();
            Cell detectAndAddHit = detectAndAddHit(x, y);
            int size2 = this.mPattern.size();
            if (detectAndAddHit != null && size2 == 1) {
                this.mPatternInProgress = true;
                notifyPatternStarted();
            }
            float abs = Math.abs(x - this.mInProgressX) + Math.abs(y - this.mInProgressY);
            float f5 = this.mSquareWidth;
            if (abs > 0.01f * f5) {
                float f6 = this.mInProgressX;
                float f7 = this.mInProgressY;
                this.mInProgressX = x;
                this.mInProgressY = y;
                if (this.mPatternInProgress && size2 > 0) {
                    ArrayList<Cell> arrayList = this.mPattern;
                    float f8 = f5 * this.mDiameterFactor * 0.5f;
                    int i4 = size2 - 1;
                    Cell cell = arrayList.get(i4);
                    float centerXForColumn = getCenterXForColumn(cell.mColumn);
                    float centerYForRow = getCenterYForRow(cell.mRow);
                    Rect rect = this.mInvalidate;
                    if (centerXForColumn < x) {
                        f = x;
                        x = centerXForColumn;
                    } else {
                        f = centerXForColumn;
                    }
                    if (centerYForRow < y) {
                        f2 = y;
                        y = centerYForRow;
                    } else {
                        f2 = centerYForRow;
                    }
                    i = historySize;
                    i2 = i3;
                    rect.set((int) (x - f8), (int) (y - f8), (int) (f + f8), (int) (f2 + f8));
                    if (centerXForColumn >= f6) {
                        centerXForColumn = f6;
                        f6 = centerXForColumn;
                    }
                    if (centerYForRow >= f7) {
                        centerYForRow = f7;
                        f7 = centerYForRow;
                    }
                    rect.union((int) (centerXForColumn - f8), (int) (centerYForRow - f8), (int) (f6 + f8), (int) (f7 + f8));
                    if (detectAndAddHit != null) {
                        float centerXForColumn2 = getCenterXForColumn(detectAndAddHit.mColumn);
                        float centerYForRow2 = getCenterYForRow(detectAndAddHit.mRow);
                        if (size2 >= 2) {
                            Cell cell2 = arrayList.get(i4 - (size2 - size));
                            f3 = getCenterXForColumn(cell2.mColumn);
                            f4 = getCenterYForRow(cell2.mRow);
                            if (centerXForColumn2 >= f3) {
                                f3 = centerXForColumn2;
                                centerXForColumn2 = f3;
                            }
                            if (centerYForRow2 >= f4) {
                                f4 = centerYForRow2;
                                centerYForRow2 = f4;
                            }
                        } else {
                            f3 = centerXForColumn2;
                            f4 = centerYForRow2;
                        }
                        float f9 = this.mSquareWidth / 2.0f;
                        float f10 = this.mSquareHeight / 2.0f;
                        rect.set((int) (centerXForColumn2 - f9), (int) (centerYForRow2 - f10), (int) (f3 + f9), (int) (f4 + f10));
                    }
                    invalidate(rect);
                } else {
                    i = historySize;
                    i2 = i3;
                    invalidate();
                }
            } else {
                i = historySize;
                i2 = i3;
            }
            i3 = i2 + 1;
            historySize = i;
            motionEvent2 = motionEvent;
        }
    }

    private void sendAccessEvent(int i) {
        if (Build.VERSION.SDK_INT < 16) {
            setContentDescription(this.mContext.getString(i));
            sendAccessibilityEvent(4);
            setContentDescription(null);
            return;
        }
        ViewCompat_v16.announceForAccessibility(this, this.mContext.getString(i));
    }

    private void handleActionUp(MotionEvent motionEvent) {
        if (this.mPattern.isEmpty()) {
            return;
        }
        this.mPatternInProgress = false;
        notifyPatternDetected();
        invalidate();
    }

    private void handleActionDown(MotionEvent motionEvent) {
        resetPattern();
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        Cell detectAndAddHit = detectAndAddHit(x, y);
        if (detectAndAddHit != null) {
            this.mPatternInProgress = true;
            this.mPatternDisplayMode = DisplayMode.Correct;
            notifyPatternStarted();
        } else {
            this.mPatternInProgress = false;
            notifyPatternCleared();
        }
        if (detectAndAddHit != null) {
            float centerXForColumn = getCenterXForColumn(detectAndAddHit.mColumn);
            float centerYForRow = getCenterYForRow(detectAndAddHit.mRow);
            float f = this.mSquareWidth / 2.0f;
            float f2 = this.mSquareHeight / 2.0f;
            invalidate((int) (centerXForColumn - f), (int) (centerYForRow - f2), (int) (centerXForColumn + f), (int) (centerYForRow + f2));
        }
        this.mInProgressX = x;
        this.mInProgressY = y;
    }

    private float getCenterXForColumn(int i) {
        float f = this.mSquareWidth;
        return (i * f) + 0.0f + (f / 2.0f);
    }

    private float getCenterYForRow(int i) {
        float f = this.mSquareHeight;
        return (i * f) + 0.0f + (f / 2.0f);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        boolean z;
        int elapsedRealtime;
        ArrayList<Cell> arrayList = this.mPattern;
        int size = arrayList.size();
        boolean[][] zArr = this.mPatternDrawLookup;
        if (this.mPatternDisplayMode == DisplayMode.Animate) {
            int elapsedRealtime2 = (((int) (SystemClock.elapsedRealtime() - this.mAnimatingPeriodStart)) % ((size + 1) * 700)) / 700;
            clearPatternDrawLookup();
            for (int i = 0; i < elapsedRealtime2; i++) {
                Cell cell = arrayList.get(i);
                zArr[cell.getRow()][cell.getColumn()] = true;
            }
            if (elapsedRealtime2 > 0 && elapsedRealtime2 < size) {
                float f = (elapsedRealtime % 700) / 700.0f;
                Cell cell2 = arrayList.get(elapsedRealtime2 - 1);
                float centerXForColumn = getCenterXForColumn(cell2.mColumn);
                float centerYForRow = getCenterYForRow(cell2.mRow);
                Cell cell3 = arrayList.get(elapsedRealtime2);
                this.mInProgressX = centerXForColumn + ((getCenterXForColumn(cell3.mColumn) - centerXForColumn) * f);
                this.mInProgressY = centerYForRow + (f * (getCenterYForRow(cell3.mRow) - centerYForRow));
            }
            invalidate();
        }
        float f2 = this.mSquareWidth;
        float f3 = this.mSquareHeight;
        this.mPathPaint.setStrokeWidth(this.mDiameterFactor * f2 * 0.2f);
        Path path = this.mCurrentPath;
        path.rewind();
        boolean z2 = !this.mInStealthMode || this.mPatternDisplayMode == DisplayMode.Wrong;
        boolean z3 = (this.mPaint.getFlags() & 2) != 0;
        this.mPaint.setFilterBitmap(true);
        if (z2) {
            int i2 = 0;
            while (true) {
                if (i2 >= size - 1) {
                    z = z3;
                    break;
                }
                Cell cell4 = arrayList.get(i2);
                int i3 = i2 + 1;
                Cell cell5 = arrayList.get(i3);
                if (!zArr[cell5.mRow][cell5.mColumn]) {
                    z = z3;
                    break;
                }
                drawArrow(canvas, (cell4.mColumn * f2) + 0.0f, (cell4.mRow * f3) + 0.0f, cell4, cell5);
                z3 = z3;
                i2 = i3;
            }
        } else {
            z = z3;
        }
        if (z2) {
            int i4 = 0;
            boolean z4 = false;
            while (i4 < size) {
                Cell cell6 = arrayList.get(i4);
                if (!zArr[cell6.mRow][cell6.mColumn]) {
                    break;
                }
                float centerXForColumn2 = getCenterXForColumn(cell6.mColumn);
                float centerYForRow2 = getCenterYForRow(cell6.mRow);
                if (i4 == 0) {
                    path.moveTo(centerXForColumn2, centerYForRow2);
                } else {
                    path.lineTo(centerXForColumn2, centerYForRow2);
                }
                i4++;
                z4 = true;
            }
            if ((this.mPatternInProgress || this.mPatternDisplayMode == DisplayMode.Animate) && z4 && size > 1) {
                path.lineTo(this.mInProgressX, this.mInProgressY);
            }
            canvas.drawPath(path, this.mPathPaint);
        }
        for (int i5 = 0; i5 < 3; i5++) {
            float f4 = (i5 * f3) + 0.0f;
            for (int i6 = 0; i6 < 3; i6++) {
                drawCircle(canvas, (int) ((i6 * f2) + 0.0f), (int) f4, zArr[i5][i6]);
            }
        }
        this.mPaint.setFilterBitmap(z);
    }

    private void drawArrow(Canvas canvas, float f, float f2, Cell cell, Cell cell2) {
        boolean z = this.mPatternDisplayMode != DisplayMode.Wrong;
        int i = cell2.mRow;
        int i2 = cell.mRow;
        int i3 = cell2.mColumn;
        int i4 = cell.mColumn;
        int i5 = (((int) this.mSquareWidth) - this.mBitmapWidth) / 2;
        int i6 = (((int) this.mSquareHeight) - this.mBitmapHeight) / 2;
        Bitmap bitmap = z ? this.mBitmapArrowGreenUp : this.mBitmapArrowRedUp;
        int i7 = this.mBitmapWidth;
        int i8 = this.mBitmapHeight;
        float min = Math.min(this.mSquareWidth / this.mBitmapWidth, 1.0f);
        float min2 = Math.min(this.mSquareHeight / this.mBitmapHeight, 1.0f);
        this.mArrowMatrix.setTranslate(f + i5, f2 + i6);
        this.mArrowMatrix.preTranslate(this.mBitmapWidth / 2, this.mBitmapHeight / 2);
        this.mArrowMatrix.preScale(min, min2);
        this.mArrowMatrix.preTranslate((-this.mBitmapWidth) / 2, (-this.mBitmapHeight) / 2);
        this.mArrowMatrix.preRotate(((float) Math.toDegrees((float) Math.atan2(i - i2, i3 - i4))) + 90.0f, i7 / 2.0f, i8 / 2.0f);
        this.mArrowMatrix.preTranslate((i7 - bitmap.getWidth()) / 2.0f, 0.0f);
        canvas.drawBitmap(bitmap, this.mArrowMatrix, this.mPaint);
    }

    private void drawCircle(Canvas canvas, int i, int i2, boolean z) {
        Bitmap bitmap;
        if (!z || (this.mInStealthMode && this.mPatternDisplayMode != DisplayMode.Wrong)) {
            Bitmap bitmap2 = this.mBitmapCircleDefault;
            bitmap = this.mBitmapBtnDefault;
        } else if (this.mPatternInProgress) {
            Bitmap bitmap3 = this.mBitmapCircleGreen;
            bitmap = this.mBitmapBtnTouched;
        } else if (this.mPatternDisplayMode == DisplayMode.Wrong) {
            Bitmap bitmap4 = this.mBitmapCircleRed;
            bitmap = this.mBitmapBtnDefault;
        } else if (this.mPatternDisplayMode == DisplayMode.Correct || this.mPatternDisplayMode == DisplayMode.Animate) {
            Bitmap bitmap5 = this.mBitmapCircleGreen;
            bitmap = this.mBitmapBtnDefault;
        } else {
            throw new IllegalStateException("unknown display mode " + this.mPatternDisplayMode);
        }
        int i3 = this.mBitmapWidth;
        int i4 = this.mBitmapHeight;
        float f = this.mSquareWidth;
        int i5 = (int) ((this.mSquareHeight - i4) / 2.0f);
        float min = Math.min(f / i3, 1.0f);
        float min2 = Math.min(this.mSquareHeight / this.mBitmapHeight, 1.0f);
        this.mCircleMatrix.setTranslate(i + ((int) ((f - i3) / 2.0f)), i2 + i5);
        this.mCircleMatrix.preTranslate(this.mBitmapWidth / 2, this.mBitmapHeight / 2);
        this.mCircleMatrix.preScale(min, min2);
        this.mCircleMatrix.preTranslate((-this.mBitmapWidth) / 2, (-this.mBitmapHeight) / 2);
        canvas.drawBitmap(bitmap, this.mCircleMatrix, this.mPaint);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        return new SavedState(super.onSaveInstanceState(), LockPatternUtils.patternToString(this.mPattern), this.mPatternDisplayMode.ordinal(), this.mInputEnabled, this.mInStealthMode, this.mEnableHapticFeedback);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setPattern(DisplayMode.Correct, LockPatternUtils.stringToPattern(savedState.getSerializedPattern()));
        this.mPatternDisplayMode = DisplayMode.values()[savedState.getDisplayMode()];
        this.mInputEnabled = savedState.isInputEnabled();
        this.mInStealthMode = savedState.isInStealthMode();
        this.mEnableHapticFeedback = savedState.isTactileFeedbackEnabled();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: group.pals.android.lib.ui.lockpattern.widget.LockPatternView$SavedState */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: group.pals.android.lib.ui.lockpattern.widget.LockPatternView.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        private final int mDisplayMode;
        private final boolean mInStealthMode;
        private final boolean mInputEnabled;
        private final String mSerializedPattern;
        private final boolean mTactileFeedbackEnabled;

        private SavedState(Parcelable parcelable, String str, int i, boolean z, boolean z2, boolean z3) {
            super(parcelable);
            this.mSerializedPattern = str;
            this.mDisplayMode = i;
            this.mInputEnabled = z;
            this.mInStealthMode = z2;
            this.mTactileFeedbackEnabled = z3;
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.mSerializedPattern = parcel.readString();
            this.mDisplayMode = parcel.readInt();
            this.mInputEnabled = ((Boolean) parcel.readValue(null)).booleanValue();
            this.mInStealthMode = ((Boolean) parcel.readValue(null)).booleanValue();
            this.mTactileFeedbackEnabled = ((Boolean) parcel.readValue(null)).booleanValue();
        }

        public String getSerializedPattern() {
            return this.mSerializedPattern;
        }

        public int getDisplayMode() {
            return this.mDisplayMode;
        }

        public boolean isInputEnabled() {
            return this.mInputEnabled;
        }

        public boolean isInStealthMode() {
            return this.mInStealthMode;
        }

        public boolean isTactileFeedbackEnabled() {
            return this.mTactileFeedbackEnabled;
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.mSerializedPattern);
            parcel.writeInt(this.mDisplayMode);
            parcel.writeValue(Boolean.valueOf(this.mInputEnabled));
            parcel.writeValue(Boolean.valueOf(this.mInStealthMode));
            parcel.writeValue(Boolean.valueOf(this.mTactileFeedbackEnabled));
        }
    }
}
