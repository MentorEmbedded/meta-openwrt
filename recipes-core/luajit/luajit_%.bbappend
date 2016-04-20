EXTRA_OEMAKE = 'CROSS=${HOST_PREFIX} \
                TARGET_CFLAGS="${TOOLCHAIN_OPTIONS} ${HOST_CC_ARCH}" \
                TARGET_LDFLAGS="${TOOLCHAIN_OPTIONS} ${TUNE_CCARGS}" \
                TARGET_SHLDFLAGS="${TOOLCHAIN_OPTIONS}"'

do_install_append () {
	mkdir -p ${D}/usr/bin
	pushd ${D}/usr/bin
	ln -s luajit lua
	popd 
}
