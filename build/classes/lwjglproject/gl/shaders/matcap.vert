#version 400

layout (location = 0) in vec3 position_in;
layout (location = 2) in vec3 normal_in;

uniform mat4 modelMat;
uniform mat4 viewMat;
uniform mat4 projMat;
uniform vec3 camPos;

out vec3 normal_v;
out vec3 eye_v;

void main(void)
{
    vec4 pos = modelMat * vec4(position_in,1);

    normal_v = normalize(mat3(modelMat) * normal_in);
    eye_v = normalize(pos.xyz - camPos);

    normal_v = mat3(viewMat) * normal_v;
    eye_v = mat3(viewMat) * eye_v;
    
    gl_Position = projMat * viewMat * pos;
}
