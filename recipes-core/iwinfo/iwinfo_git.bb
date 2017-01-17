DESCRIPTION = "Generalized Wireless Information Library (iwinfo)"
HOMEPAGE = "http://wiki.openwrt.org/doc/howto/wireless.utilities"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI = "git://git.openwrt.org/project/iwinfo.git;protocol=git;branch=master"
SRC_URI += "file://fix-make.patch"
SRC_URI += "file://luajit.patch"
		   
SRCREV = "6e67940be59e1aee5d275dd61172b257cb34d726"
S = "${WORKDIR}/git"

PR="r1"

DEPENDS = "uci luajit"
PACKAGES += "libiwinfo"
RDEPENDS_${PN} = "libiwinfo"

FILES_${PN} = "/usr/bin /usr/lib/lua/5.1/iwinfo.so"
FILES_${PN}-dev = "/usr/include /usr/lib/libiwinfo.so" 
FILES_libiwinfo = "/usr/lib/libiwinfo.so.0.0 /usr/lib/libiwinfo.so.0"
FILES_${PN}-dbg = "/usr/lib/lua/5.1/.debug/iwinfo.so /usr/lib/.debug/libiwinfo.so.0.0 /usr/src/debug/iwinfo/* /usr/bin/.debug/*"

do_compile () {
	oe_runmake 'FPIC=-fPIC' compile
}

do_install () {
	mkdir -p ${D}/${libdir} ${D}/usr/bin
	install ${B}/libiwinfo.so.0.0 ${D}/${libdir}
	cp -a ${B}/libiwinfo.so.0 ${D}/${libdir}
	cp -a ${B}/libiwinfo.so ${D}/${libdir}
	cp -a ${B}/include ${D}/usr/
	install ${B}/iwinfo ${D}/usr/bin
	mkdir -p ${D}/usr/lib/lua/5.1
	install ${B}/luaiwinfo.so ${D}/usr/lib/lua/5.1/iwinfo.so
}

