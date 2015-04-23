DESCRIPTION = "OpenWrt system process manager"
HOMEPAGE = "http://wiki.openwrt.org/doc/techref/procd"
LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://procd.c;beginline=1;endline=13;md5=61e3657604f131a859b57a40f27a9d8e"

SRC_URI = "git://nbd.name/luci2/procd.git;protocol=git;branch=master"
SRC_URI += "file://reload_config"
SRC_URI += "file://hotplug*.json"
SRC_URI += "file://procd.sh"
		   
SRCREV = "27159f21f76b973a9fa3ec92b8fee2e390d43a43"
S = "${WORKDIR}/git"

inherit cmake

PR="r1"

DEPENDS = "ubus"
RDEPENDS_${PN} = "fstools ubox ubus libubox libubus"

ALTERNATIVE_${PN} = "init"

ALTERNATIVE_PRIORITY = "200"

ALTERNATIVE_LINK_NAME[init] = "/usr/sbin/init"
ALTERNATIVE_PRIORITY[init] = "50"
FILES_${PN} += "/lib /sbin"

do_install_append () {
	mkdir -p ${D}/etc ${D}/lib/functions
	mv ${D}/usr/sbin ${D}/sbin
	install  ${WORKDIR}/reload_config ${D}/sbin/
	install  ${WORKDIR}/hotplug*.json ${D}/etc/
	install  ${WORKDIR}/procd.sh ${D}/lib/functions/

}

