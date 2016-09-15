DESCRIPTION = "OpenWrt RPC daemon"
HOMEPAGE = "http://wiki.openwrt.org/doc/techref/ubus"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=17;md5=587cc5ad76f548db3e8674df450dc88f"

SRC_URI = "git://git.openwrt.org/project/firewall3.git"
SRC_URI += "file://firewall.config"
SRC_URI += "file://firewall.hotplug"
SRC_URI += "file://firewall.init"
SRC_URI += "file://firewall.user"
		   
SRCREV = "1d0bd45db042c9dd916f15a5f4e3e5a2f63d0616"
S = "${WORKDIR}/git"

inherit cmake

PR="r1"

DEPENDS = "libubox ubus uci iptables"

FILES_${PN} += "/sbin"

do_install_append () {
	mkdir -p ${D}/etc/hotplug.d/iface ${D}/etc/config ${D}/etc/init.d ${D}/sbin
	mv ${D}/usr/sbin/firewall3 ${D}/sbin/fw3
	cp ${WORKDIR}/firewall.init ${D}/etc/init.d/firewall
	cp ${WORKDIR}/firewall.hotplug ${D}/etc/hotplug.d/iface/20-firewall
	cp ${WORKDIR}/firewall.config ${D}/etc/config/firewall
	cp ${WORKDIR}/firewall.user ${D}/etc/firewall.user
}
