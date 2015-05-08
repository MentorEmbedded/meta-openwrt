FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://dhcp.conf"
SRC_URI += "file://dnsmasq.conf"
SRC_URI += "file://dnsmasq.hotplug"
SRC_URI += "file://dnsmasq.init"

RDEPENDS_${PN} += "jsonfilter"

do_install_append () {
	mkdir -p ${D}/etc/config ${D}/etc/init.d ${D}/etc/hotplug.d/iface
	mkdir -p ${D}/tmp/hosts
	cp ${WORKDIR}/dhcp.conf ${D}/etc/config/dhcp
	cp ${WORKDIR}/dnsmasq.conf ${D}/etc/
	cp ${WORKDIR}/dnsmasq.init ${D}/etc/init.d/dnsmasq
	cp ${WORKDIR}/dnsmasq.hotplug ${D}/etc/hotplug.d/iface/25-dnsmasq
}

