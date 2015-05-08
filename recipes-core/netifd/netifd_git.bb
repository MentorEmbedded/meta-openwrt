DESCRIPTION = "Network Interface Daemon"
HOMEPAGE = "http://wiki.openwrt.org/doc/techref/netifd"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=13;md5=572cd47ba0e377b26331e67e9f3bc4b3"

SRC_URI = "git://git.openwrt.org/project/netifd.git;protocol=git;branch=master"
SRC_URI += "file://files"
SRC_URI += "file://fix-compile.patch"
		   
SRCREV = "46c569989f984226916fec28dd8ef152a664043e"
S = "${WORKDIR}/git"

inherit cmake

PR="r1"

DEPENDS = "ubus uci libubox libnl"
FILES_${PN} += "/lib /usr/share"

do_install_append () {
	cp -a ${WORKDIR}/files/* ${D}/
	mv ${D}/usr/sbin/netifd ${D}/sbin
	cp -a ${S}/scripts/* ${D}/lib/netifd
	rmdir ${D}/usr/sbin
}
