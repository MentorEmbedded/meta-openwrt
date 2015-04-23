DESCRIPTION = "This package provides the UBUS RPC backend server to expose various functionality to frontend programs via JSON-RPC."
HOMEPAGE = "http://wiki.openwrt.org/doc/techref/rpcd"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=18;md5=da5faf55ed0618f0dde1c88e76a0fc74"

SRC_URI = "git://nbd.name/luci2/rpcd.git;protocol=git;branch=master"
SRC_URI += "file://rpcd.init"
SRC_URI += "file://rpcd.config"
		   
SRCREV = "311c85e7d9a8f7fee17e65afc371f4fd0c8cd588"
S = "${WORKDIR}/git"

inherit cmake

PR="r1"

DEPENDS = "ubus uci iwinfo"

#FIXME: put plugins to the correct place
FILES_${PN} += "/usr/lib/*.so"

#FIXME: install rpcd directly to /sbin from makefile instead of usig mv
do_install_append () {
	mkdir -p ${D}/${sysconfdir}/init.d ${D}/${sysconfdir}/config ${D}/usr/share/rpcd/acl.d
	install ${WORKDIR}/rpcd.config ${D}/${sysconfdir}/config/rpcd
	install ${WORKDIR}/rpcd.init ${D}/${sysconfdir}/init.d/rpcd 
	install ${WORKDIR}/rpcd.init ${D}/${sysconfdir}/init.d/rpcd
	install ${S}/unauthenticated.json ${D}/usr/share/rpcd/acl.d
	cp -a ${S}/include ${D}/usr/include
	mv ${D}/usr/sbin ${D}/sbin  
}

