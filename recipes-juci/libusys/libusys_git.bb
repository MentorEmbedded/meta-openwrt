# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "micro system library (for use with ubus2)"

HOMEPAGE = "https://github.com/mkschreder/libusys"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://src/uloop.c;beginline=1;endline=17;md5=9bed33188dd18fa8fec97a710e234273"
SECTION = "libs"

DEPENDS += "libutype"
RDEPENDS_${PN} += "libutype"

SRC_URI = "git://github.com/mkschreder/libusys.git;protocol=git;branch=master"
SRCREV = "dd7b53bf45baa1765f2163a81fd53a422e3b4fa8"

inherit autotools

S = "${WORKDIR}/git"
B = "${WORKDIR}/git"

EXTRA_OEMAKE = "DESTDIR=${D} BUILD_DIR=${B}"

TARGET_CC_ARCH += "${LDFLAGS}"

FILES_SOLIBSDEV = ""
FILES_${PN} = "${libdir}/*.so"
