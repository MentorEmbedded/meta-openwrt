DESCRIPTION = "C Frontend for zmq. The ZeroMQ lightweight messaging kernel is a library which extends the standard socket interfaces with features traditionally provided by specialised messaging middleware products."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;;md5=9741c346eef56131163e13b9db1241b3"

SRC_URI = "git://github.com/zeromq/czmq.git;protocol=git;branch=master"
SRCREV = "b3c83a3f1cd9204b9135841ac2f8b9c1a05efd21"

PR="r1"
inherit autotools
S = "${WORKDIR}/git"
B = "${S}"

DEPENDS = "zeromq"
RDEPENDS_${PN} = "zeromq"


CFLAGS_append = " -std=c99 "

FILES_${PN} += "/usr/lib/"
do_configure () {
	./autogen.sh
	oe_runconf
}

do_install_append () {
}

