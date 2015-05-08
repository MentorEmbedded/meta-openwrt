DESCRIPTION = "Json filter"
HOMEPAGE = "http://wiki.openwrt.org/"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=15;md5=60fb698e1ae531930b377e9f77d3ba1f"

SRC_URI = "git://git.openwrt.org/project/jsonpath.git;protocol=git;branch=master"
SRC_URI += "file://fix-lemon.patch"
		   
SRCREV = "cdc760c58077f44fc40adbbe41e1556a67c1b9a9"
S = "${WORKDIR}/git"
B = "${WORKDIR}/git"
PR="r1"

inherit cmake

DEPENDS = "libubox json-c lemon-native"

FILES_${PN} = "/usr/bin"

do_install_append () {
	mv ${D}/usr/bin/jsonpath ${D}/usr/bin/jsonfilter
}
