DESCRIPTION = "Scripted ubus object management daemon"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://src/main.c;beginline=1;endline=6;md5=99c48b8ec1e0e764e678d147af094dc1"

SRC_URI = "git://github.com/mkschreder/ubus-scriptd.git;protocol=git;branch=master"
SRC_URI += "file://ubus-scriptd.init"
SRC_URI += "file://fix-luajit.patch"
SRC_URI += "file://fix-bsd-sources.patch"
SRCREV = "d7cf4e1e820365073684f0e095e831ca7df0a360"
S = "${WORKDIR}/git"
B = "${S}"

PR="r1"

DEPENDS = "luajit ubus libubox uci"
RDEPENDS_${PN} = "luajit ubus libubox uci"

CFLAGS_append = " -std=gnu99 $(shell pkg-config --cflags luajit)"

FILES_${PN} += "/lib /sbin /usr/lib/ubus /usr/lib/ubus-services "

do_install_append () {
	mkdir -p ${D}/usr/lib/ubus/
	mkdir -p ${D}/usr/lib/ubus-services/
	mkdir -p ${D}/etc/init.d/
	install -m 722 ${WORKDIR}/ubus-scriptd.init ${D}/etc/init.d/ubus-scriptd
	mkdir -p ${D}/sbin
	install -m 722 ${B}/build_dir/ubus-scriptd ${D}/sbin/ubus-scriptd
}

