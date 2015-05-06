DESCRIPTION = "Basic utility library"
HOMEPAGE = "http://wiki.openwrt.org/doc/uci"
LICENSE = "ISC BSD-3c"
LIC_FILES_CHKSUM = "file://uloop.c;beginline=4;endline=17;md5=ae045d075c7d6dde096232b6130a5202"

SRC_URI = "git://git.openwrt.org/project/libubox.git;protocol=git;branch=master"
SRC_URI += "file://luajit.patch"
		   
SRCREV = "b8d9b382e39823850331edc2a92379173daf1be3"
S = "${WORKDIR}/git"

inherit cmake

PR="r1"

DEPENDS = "json-c luajit"
RDEPENDS_${PN} = "lua-socket"
EXTRA_OECMAKE = "-DLUAPATH=/usr/lib/lua/5.1"

FILES_${PN} += "/usr/lib/lua/5.1 /lib/"
FILES_${PN}-dbg += "/usr/lib/lua/5.1/.debug"
FILES_${PN}-dev = "/usr"

do_install_append () {
	mkdir ${D}/lib
	install ${D}/usr/lib/libubox.so ${D}/lib
	install ${D}/usr/lib/libblobmsg_json.so ${D}/lib
	install ${D}/usr/lib/libjson_script.so ${D}/lib
	install ${S}/examples/uloop-example.lua ${D}/usr/lib/lua/5.1/
}

