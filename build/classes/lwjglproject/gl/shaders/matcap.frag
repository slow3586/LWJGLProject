#version 400

uniform sampler2D matcapTexture;

in vec3 normal_v;
in vec3 eye_v;

vec2 matcap(vec3 eye, vec3 normal) {
    vec3 reflected = reflect(eye, normal);
    float m = 2.8284271247461903 * sqrt( reflected.z+3 );
    return reflected.xy / m + 0.5;
}

void main() {
    vec2 muv = matcap(eye_v, normal_v);

    gl_FragColor = texture2D(matcapTexture, vec2(muv.x, 1.0-muv.y));
}
