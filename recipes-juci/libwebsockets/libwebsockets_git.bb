# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "lightweight C websockets library"
HOMEPAGE = "https://github.com/mkschreder/libblobpack"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=041a1dec49ec8a22e7f101350fd19550"
SECTION = "libs"

DEPENDS += "zlib openssl"

SRCREV = "0c7e5a94182b8bf5b5be2fc58141466bf54d5812"
SRC_URI = "git://github.com/warmcat/libwebsockets"

S = "${WORKDIR}/git"
inherit cmake
PACKAGES += "${PN}-test"
FILES_${PN}-dev += "${libdir}/cmake"
FILES_${PN}-test += "${datadir}/"

