# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Revolution RPC Server (RevoRPCD)"
HOMEPAGE = "https://github.com/mkschreder/jucid"
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=ecb319022da02987a5c1a92120412393"
SECTION = "apps"

SRCREV = "ae41b9aae88e002511f80607f2526403b786ed98"
SRC_URI = "git://github.com/mkschreder/orangerpcd \
		file://fix_luajit.patch \
           "

S = "${WORKDIR}/git"

inherit autotools pkgconfig

do_compile () {
	mkdir -p node_modules
	oe_runmake -C src liborange.la
	oe_runmake
}

EXTRA_OECONF = " LIBLUA_LINK=-lluajit "
CFLAGS_append = " -D_DEFAULT_SOURCE -std=gnu99 `pkg-config --silence-errors --cflags luajit`"
CFLAGS_remove = " -D_BSD_SOURCE "
LDFLAGS_append = " `pkg-config --silence-errors --libs luajit` "
DEPENDS += "libblobpack libutype libusys luci uci luajit libwebsockets iwinfo rpcd ubus"
RDEPENDS_${PN} += "libutype libblobpack libusys"
