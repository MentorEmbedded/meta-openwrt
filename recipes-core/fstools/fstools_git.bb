DESCRIPTION = "OpenWrt filesystem tools"
HOMEPAGE = "http://wiki.openwrt.org/"
LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://block.c;beginline=1;endline=14;md5=45e915598006ba7929c617cda79ab0fc"

SRC_URI = "git://git.openwrt.org/project/fstools.git"
SRC_URI += "file://fstab.init"
SRC_URI += "file://fstab.default"
SRC_URI += "file://mount.hotplug"
SRC_URI += "file://snapshot"
SRC_URI += "file://fix-bsd.patch"
		   
SRCREV = "914b023e71559e033ec5a1f9840511eb1ccaf386"
S = "${WORKDIR}/git"

inherit cmake

PR="r1"

DEPENDS = "ubox libubox"
PACKAGES += "libfstools"
FILES_${PN} = "/sbin ${sysconfdir} /usr/sbin"
FILES_${PN}-dev = "/usr/lib /usr/include"
FILES_libfstools = "${base_libdir}"
RDEPENDS_${PN} += "libfstools libubox libuci"
RDEPENDS_libfstools += "libubox"

do_install_append () {
        mkdir -p ${D}/sbin ${D}/lib ${D}/usr/sbin ${D}/etc/hotplug.d/block ${D}/etc/init.d/ ${D}/etc/uci-defaults/
        
	#FIXME: install directly to /sbin from build not using mv 
	mv ${D}/usr/sbin/{mount_root,jffs2reset,snapshot_tool} ${D}/sbin/
        install ${D}/usr/lib/libfstools.so ${D}/lib/
        install ${WORKDIR}/snapshot ${D}/sbin/

        install ${WORKDIR}/fstab.init ${D}/etc/init.d/fstab
        install ${WORKDIR}/fstab.default ${D}/etc/uci-defaults/10-fstab
        install ${WORKDIR}/mount.hotplug ${D}/etc/hotplug.d/block/10-mount

	#FIXME: install directly to /sbin from build not using mv 
        mv ${D}/usr/sbin/block ${D}/sbin/
        install ${D}/usr/lib/libblkid-tiny.so ${D}/lib/
        ln -s /sbin/block ${D}/usr/sbin/swapon
        ln -s /sbin/block ${D}/usr/sbin/swapoff
	mv ${D}/usr/lib/libblkid-tiny.so ${D}/usr/lib/libblkid-tiny.so.1
	ln -s /usr/lib/libblkid-tiny.so.1 ${D}/usr/lib/libblkid-tiny.so
	mv ${D}/usr/lib/libfstools.so ${D}/usr/lib/libfstools.so.1
	ln -s /usr/lib/libfstools.so.1 ${D}/usr/lib/libfstools.so
}

