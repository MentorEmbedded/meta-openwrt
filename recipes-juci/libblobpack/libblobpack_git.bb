# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "A library for packing arbitrary data into binary cross platform blobs"
HOMEPAGE = "https://github.com/mkschreder/libblobpack"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://README.md;md5=68c134fe97988f3855be33251a383648"
SECTION = "libs"

SRCREV = "4bb494cf13581c20e86981321470aa46dad46aa3"
SRC_URI = "git://github.com/mkschreder/libblobpack \
           "

S = "${WORKDIR}/git"
inherit autotools

FILES_${PN} = "${libdir}/*.so.*"

