FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://pkgconfig"

inherit pkgconfig

do_install_append () {
	cp -a ${WORKDIR}/pkgconfig ${D}/usr/lib
}
