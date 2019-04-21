#设置要编译 c 文件的 路径（多个 c 文件）
aux_source_directory(src/main/cpp SRC_LIST)
add_library( # Sets the name of the library.
             luajava
             # Sets the library as a shared library.
             SHARED
             # Provides a relative path to your source file(s).
             ${SRC_LIST}
             )
find_library( # Sets the name of the path variable.
              log-lib )
target_link_libraries( # Specifies the target library.
                       luajava
                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/src/main/jniLibs/${ANDROID_ABI})