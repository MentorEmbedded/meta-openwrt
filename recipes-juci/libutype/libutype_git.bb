# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "micro types library for use with ubus2"

HOMEPAGE = "https://github.com/mkschreder/libutype"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://README.md;md5=d41d8cd98f00b204e9800998ecf8427e"
SECTION = "libs"

SRCREV = "e1c7683eb6ec32a933423e52dccdca11a8a5e62b"
SRC_URI = "git://github.com/mkschreder/libutype \
"

S = "${WORKDIR}/git"

inherit autotools

#EXTRA_OEMAKE = "DESTDIR=${D} BUILD_DIR=${B}"

FILES_SOLIBSDEV = "${libdir}/*.so"
FILES_${PN} += "${libdir}/*.so.*"
FILES_${PN}-dev += "${libdir}/*.so"

RDEPENDS_${PN} += "libusys"
