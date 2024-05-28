package com.baidu.cloud.mediaprocess.basefilters;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class VideoARGBFilter extends ImageFilter {
    public VideoARGBFilter() {
        super("uniform mat4 uMVPMatrix;uniform mat4 uTexMatrix;attribute vec4 position;attribute vec4 inputTextureCoordinate;varying vec2 textureCoordinate;void main() {    gl_Position = uMVPMatrix * position;    textureCoordinate = (uTexMatrix * inputTextureCoordinate).xy;}", "precision mediump float;\nvarying vec2 textureCoordinate;\nuniform sampler2D inputImageTexture;\nvoid main() {\n    vec4 color = texture2D(inputImageTexture, textureCoordinate);\n    gl_FragColor = vec4(color.a, color.r, color.g, color.b);\n}\n");
    }
}
