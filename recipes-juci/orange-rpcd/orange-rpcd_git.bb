# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Revolution RPC Server (RevoRPCD)"
HOMEPAGE = "https://github.com/mkschreder/jucid"
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=ecb319022da02987a5c1a92120412393"
SECTION = "apps"

SRCREV = "638a7b5461e4072da3292b8b83b07727f71c6435"
SRC_URI = "git://github.com/mkschreder/orangerpcd \
		file://fix_luajit.patch \
		file://access.json \
		file://orange.config \
		file://orange.init \
		file://uci-defaults.sh \
           "

S = "${WORKDIR}/git"

inherit autotools pkgconfig

do_compile () {
	mkdir -p node_modules
	oe_runmake -C src liborange.la
	oe_runmake
}

do_install_append () {
	install -d ${D}/etc/init.d
	install -m 750 ${WORKDIR}/orange.init ${D}/etc/init.d/orange
	install -d ${D}/etc/config
	install -m 644 ${WORKDIR}/orange.config ${D}/etc/config/orange
	install -d ${D}/etc/uci-defaults
	install -m 750 ${WORKDIR}/uci-defaults.sh ${D}/etc/uci-defaults/00-orange-rpcd.sh
	install -d ${D}/usr/lib/orange/api/
	cp -r ${S}/plugins/* ${D}/usr/lib/orange/api/
	install -d ${D}/usr/lib/orange/lib/
	cp -r ${S}/lualib/* ${D}/usr/lib/orange/lib/
	install -d ${D}/usr/share/rpcd/acl.d/
	install -m 644 ${WORKDIR}/access.json ${D}/usr/share/rpcd/acl.d/orange.json
	install -m 750 ${S}/orangectl.sh ${D}/usr/bin/orangectl
	install -d ${D}/usr/lib/orange/hooks/
}

EXTRA_OECONF = " LIBLUA_LINK=-lluajit "
CFLAGS_append = " -D_DEFAULT_SOURCE -std=gnu99 `pkg-config --silence-errors --cflags luajit`"
CFLAGS_remove = " -D_BSD_SOURCE "
LDFLAGS_append = " `pkg-config --silence-errors --libs luajit` "
DEPENDS += "libblobpack libutype libusys uci luajit libwebsockets iwinfo rpcd ubus"
RDEPENDS_${PN} += "libutype libblobpack libusys"

FILES_${PN} += "/usr/share/rpcd/acl.d/* /usr/lib/orange/* /usr/lib/orange/api/* /usr/lib/orange/plugins/*"
