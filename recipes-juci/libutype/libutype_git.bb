# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "micro types library for use with ubus2"

HOMEPAGE = "https://github.com/mkschreder/libutype"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://README.md;md5=d41d8cd98f00b204e9800998ecf8427e"
SECTION = "libs"

SRC_URI = "git://github.com/mkschreder/libutype.git;protocol=git;branch=master"
SRCREV = "0cfa35bbb4948d6926aa85d3e48f2318b9166486"

S = "${WORKDIR}/git"

inherit autotools

#EXTRA_OEMAKE = "DESTDIR=${D} BUILD_DIR=${B}"

FILES_SOLIBSDEV = ""
FILES_${PN} = "${libdir}/*.so.*"

RDEPENDS_${PN} += "libusys"
