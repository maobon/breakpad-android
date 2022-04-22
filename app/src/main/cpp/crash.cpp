#include <stdio.h>
#include <jni.h>


/**
 * 引起 crash
 */
void Crash() {
    volatile int *a = (int *) (NULL);
    *a = 1;
}

extern "C"
JNIEXPORT void JNICALL
Java_fun_learnlife_breakpaddemo_MainActivity_crash(JNIEnv *env, jobject obj) {
    Crash();
}