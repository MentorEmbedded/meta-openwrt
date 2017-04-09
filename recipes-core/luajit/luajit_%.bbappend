DEPENDS += "ldconfig-native"

EXTRA_OEMAKE_append = ' \
                TARGET_CFLAGS="${TOOLCHAIN_OPTIONS} ${HOST_CC_ARCH}" \
                TARGET_LDFLAGS="${TOOLCHAIN_OPTIONS} ${TUNE_CCARGS}" \
                TARGET_SHLDFLAGS="${TOOLCHAIN_OPTIONS}"'

TUNE_CCARGS_class-native = ""
EXTRA_OEMAKE_class-native_append = ' \
                TARGET_CFLAGS="${TOOLCHAIN_OPTIONS} ${HOST_CC_ARCH}" \
                TARGET_LDFLAGS="${TOOLCHAIN_OPTIONS}" \
                TARGET_SHLDFLAGS="${TOOLCHAIN_OPTIONS}"'

do_install_append () {
	mkdir -p ${D}/usr/bin
	pushd ${D}/usr/bin
	ln -s luajit lua
	popd 
}

do_install_class-native () {
	oe_runmake ${EXTRA_OEMAKEINST} install
}
