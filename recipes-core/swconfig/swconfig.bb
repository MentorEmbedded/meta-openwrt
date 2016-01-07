DESCRIPTION = "Generalized Wireless Information Library (iwinfo)"
HOMEPAGE = "http://wiki.openwrt.org/doc/howto/wireless.utilities"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://cli.c;beginline=1;endline=15;md5=0ef6f7a96f52f53fbc9e67f0dacdfeb4"

SRC_URI = "file://src"
SRC_URI += "file://switch.sh"
		   
S = "${WORKDIR}/src"

PR="r1"

DEPENDS = "uci libnl"

do_compile () {
	oe_runmake all
}

do_install () {
	mkdir -p ${D}/${libdir} ${D}/usr/bin
	install ${B}/libiwinfo.so.0.0 ${D}/${libdir}
	cp -a ${B}/libiwinfo.so.0 ${D}/${libdir}
	cp -a ${B}/libiwinfo.so ${D}/${libdir}
	cp -a ${B}/include ${D}/usr/
	install ${B}/iwinfo ${D}/usr/bin
}

