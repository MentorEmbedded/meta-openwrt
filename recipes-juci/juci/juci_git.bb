DESCRIPTION = "Scripted ubus object management daemon"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;;;md5=3b5cabd0e344cf2565cd337b7680a208"

SRC_URI = "git://git@bitbucket.org/spbteam/juci;protocol=ssh;branch=dga900"
SRCREV = "b8deb73371979161738a23689c19d471d640366a"

SRC_URI += "file://40-juci-openwrt-config"
SRC_URI += "file://user-admin.acl"
SRC_URI += "file://fix_host_uid_leak.patch"

S = "${WORKDIR}/git"
B = "${S}"

PR="r1"


DEPENDS = "lighttpd luajit orange-rpcd"
RDEPENDS_${PN} = "lighttpd luajit orange-rpcd"

FILES_${PN} += " /usr/lib/* /usr/share/* /etc/* /sbin/* /www/* "

do_compile () {
	oe_runmake clean
	oe_runmake
	touch Makefile.local
	touch .cleaned
	oe_runmake CONFIG_PACKAGE_juci-ddns=y
	oe_runmake CONFIG_PACKAGE_juci-dnsmasq-dhcp=y
	oe_runmake CONFIG_PACKAGE_juci-dropbear=y
	oe_runmake CONFIG_PACKAGE_juci-ethernet=y
	oe_runmake CONFIG_PACKAGE_juci-event=y
	oe_runmake CONFIG_PACKAGE_juci-firewall-fw3=y
	oe_runmake CONFIG_PACKAGE_juci-simple-gui=y
	oe_runmake CONFIG_PACKAGE_juci-macdb=y
	oe_runmake CONFIG_PACKAGE_juci-minidlna=y
	oe_runmake CONFIG_PACKAGE_juci-mod-status=y
	oe_runmake CONFIG_PACKAGE_juci-mod-system=y
	oe_runmake CONFIG_PACKAGE_juci-network-netifd=y
	oe_runmake CONFIG_PACKAGE_juci-openwrt-wireless=y
	oe_runmake CONFIG_PACKAGE_juci-samba=y
	oe_runmake CONFIG_PACKAGE_juci-snmpd=y
	oe_runmake CONFIG_PACKAGE_juci-sysupgrade=y
	oe_runmake CONFIG_PACKAGE_juci-uhttpd=y
	oe_runmake CONFIG_PACKAGE_juci-upnp=y
	oe_runmake CONFIG_PACKAGE_juci-usb=y
	oe_runmake CONFIG_PACKAGE_juci-utils=y
	oe_runmake CONFIG_PACKAGE_juci-theme-openwrt=y
	oe_runmake CONFIG_PACKAGE_juci=y
}

do_install () {
	oe_runmake DESTDIR=${D} install
	cp ${S}/juci_redirect.sh ${D}/www/cgi-bin/luci
	mkdir -p ${D}/sbin
	cp ${S}/juci-update ${D}/sbin/
	mkdir -p ${D}/etc/config
	cp ${S}/juci.config.example ${D}/etc/config/juci
	install -d ${D}/usr/lib/juci/acl
	install -m 644 ${WORKDIR}/user-admin.acl ${D}/usr/lib/juci/acl
	install -d ${D}/etc/uci-defaults
	install -m 644 ${WORKDIR}/40-juci-openwrt-config ${D}/etc/uci-defaults/
}
