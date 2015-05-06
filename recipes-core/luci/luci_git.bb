DESCRIPTION = "Generalized Wireless Information Library (iwinfo)"
HOMEPAGE = "http://wiki.openwrt.org/doc/howto/wireless.utilities"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2b42edef8fa55315f34f2370b4715ca9"

SRC_URI = "git://git.openwrt.org/project/luci.git;protocol=git;branch=luci-0.12"
SRC_URI += "file://fix-compile.patch"
SRC_URI += "file://fix-compile-luajit.patch"
SRC_URI += "file://luajit.patch"
		   
SRCREV = "f1e2a26f4d315aaa7db5ac88e529a4854911f387"
S = "${WORKDIR}/git"

PR="r1"

DEPENDS = "uci luajit"

EXTRA_OEMAKE='CC="${CC}"'
#PARALLEL_MAKE = ""
FILES_${PN} += "/"
FILES_${PN}-dbg += " \
			/usr/lib/lua/5.1/.debug/ \
			/usr/lib/lua/5.1/luci/template/.debug \
			/usr/local/share/lua/5.1/.debug/ \
			/usr/local/share/lua/5.1/luci/template/.debug \
		"

do_compile () {
	oe_runmake
}

do_install () {
	oe_runmake hostcopy
	cp -a ${B}/host/* ${D}
	rm -rf ${D}/tmp
	rm -rf ${D}/var
}

