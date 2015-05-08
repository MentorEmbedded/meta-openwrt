DESCRIPTION = "Base files from openwrt project"
HOMEPAGE = "http://wiki.openwrt.org/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=94d55d512a9ba36caa9b7df079bae19f"

SRC_URI = "git://git.openwrt.org/14.07/openwrt.git;protocol=git;branch=master"
SRC_URI += "file://LICENSE"
SRC_URI += "file://fix-network-config.patch"
		   
SRCREV = "229d60fdb45c34902d402938e231c006f7c73931"
S = "${WORKDIR}/git"

PR="r1"

DEPENDS = ""

FILES_${PN} = "*"

do_compile () {
	:
}

do_install () {
	cp -a ${S}/package/base-files/files/* ${D}
        mkdir -p ${D}/CONTROL
        mkdir -p ${D}/dev
        mkdir -p ${D}/etc/crontabs
        mkdir -p ${D}/etc/rc.d
        mkdir -p ${D}/overlay
        mkdir -p ${D}/lib/firmware
        mkdir -p ${D}/mnt
        mkdir -p ${D}/proc
        mkdir -p ${D}/tmp
        mkdir -p ${D}/usr/lib
        mkdir -p ${D}/usr/bin
        mkdir -p ${D}/sys
        mkdir -p ${D}/www
        mkdir -p ${D}/root
        ln -sf /proc/mounts ${D}/etc/mtab
        mkdir -p ${D}/etc
	rm -rf ${D}/var
        #ln -sf ./tmp ${D}/var
        ln -sf /tmp/resolv.conf /tmp/fstab /tmp/TZ ${D}/etc/
        chmod 0600 ${D}/etc/shadow
        chmod 1777 ${D}/tmp

}

PACKAGE_ARCH = "${MACHINE_ARCH}"
CONFFILES_${PN} = "${sysconfdir}/fstab ${@['', '${sysconfdir}/hostname'][(d.getVar('hostname', True) != '')]} ${sysconfdir}/shells"

