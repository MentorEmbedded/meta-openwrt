DESCRIPTION = "ustream SSL Library"
HOMEPAGE = "http://wiki.openwrt.org"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://ustream-ssl.c;beginline=1;endline=17;md5=f633104677420342f142ab4835e04031"

SRC_URI = "git://git.openwrt.org/project/ustream-ssl.git"
		   
SRCREV = "a4ca61527236e89eb9efb782fd9bfd04796144e3"
S = "${WORKDIR}/git"

inherit cmake

PR="r1"

DEPENDS = "libubox openssl"
FILES_${PN} = "/lib"
FILES_${PN}-dev = "/usr"

do_install_append () {
	cp -a ${D}/usr/lib ${D}/lib
}
