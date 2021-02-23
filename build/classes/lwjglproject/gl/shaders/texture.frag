#version 400
    
in vec2 uv_v;
uniform sampler2D sampler;

layout(location = 0) out vec4 diffuseColor;

void main() {
    diffuseColor = texture2D ( sampler, vec2(uv_v.x, 1.0-uv_v.y) );
    //diffuseColor = vec4(uv_v.x, uv_v.y, 0, 1);
}