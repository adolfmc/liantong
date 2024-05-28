package com.baidu.cloud.mediaprocess.basefilters;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ImageExtTexFilter extends ImageFilter {
    public ImageExtTexFilter() {
        super("uniform mat4 uMVPMatrix;uniform mat4 uTexMatrix;attribute vec4 position;attribute vec4 inputTextureCoordinate;varying vec2 textureCoordinate;void main() {    gl_Position = uMVPMatrix * position;    textureCoordinate = (uTexMatrix * inputTextureCoordinate).xy;}", "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 textureCoordinate;\nuniform samplerExternalOES inputImageTexture;\nvoid main() {\n    gl_FragColor = texture2D(inputImageTexture, textureCoordinate);\n}\n");
        this.mTextureTarget = 36197;
    }
}
