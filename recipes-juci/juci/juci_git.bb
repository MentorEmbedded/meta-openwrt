DESCRIPTION = "Scripted ubus object management daemon"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;;;md5=87212b5f1ae096371049a12f80034f32"

SRC_URI = "git://github.com/mkschreder/juci.git;protocol=git;branch=master"
SRC_URI += "file://fix_luajit.patch"
SRCREV = "07121275b8c07296663b015847418d33477047f2"
S = "${WORKDIR}/git"
B = "${S}"

PR="r1"

export CONFIG_PACKAGE_juci-ubus-core = "y"

DEPENDS = "libuv-lua"
RDEPENDS_${PN} = "libuv-lua"

FILES_${PN}-dbg += "/usr/lib/rpcd/.debug /usr/libexec/rpcd/.debug"
FILES_${PN} += "/usr/libexec/rpcd/juci-ubus-core-cgi /usr/lib/* /usr/lib/rpcd/*.so /usr/libexec/rpcd/*.so /usr/lib/ubus* /usr/share/* /etc/* /sbin/* /www/* "

do_configure_prepend () {
	pushd ${B}
	#./bootstrap.sh
	popd
}

do_install_append () {
	:
}

do_compile () {
	oe_runmake clean
	oe_runmake
	touch Makefile.local
	touch .cleaned
	oe_runmake CONFIG_PACKAGE_juci=y
	oe_runmake CONFIG_PACKAGE_juci-router-openwrt=y
	oe_runmake CONFIG_PACKAGE_juci-theme-inteno=y
	oe_runmake CONFIG_PACKAGE_juci-mod-status=y
	oe_runmake CONFIG_PACKAGE_juci-mod-system=y
	oe_runmake CONFIG_PACKAGE_juci-event=y
	oe_runmake CONFIG_PACKAGE_juci-jquery-console=y
	oe_runmake CONFIG_PACKAGE_juci-ethernet=y
	oe_runmake CONFIG_PACKAGE_juci-inteno-router=y
	oe_runmake CONFIG_PACKAGE_juci-firewall-fw3=y
	oe_runmake CONFIG_PACKAGE_juci-netmode=y
	oe_runmake CONFIG_PACKAGE_juci-network-netifd=y
	oe_runmake CONFIG_PACKAGE_juci-openwrt-wireless=y
}

do_install () {
	oe_runmake DESTDIR=${D} install
	cp ${S}/juci_redirect.sh ${D}/www/cgi-bin/luci
	mkdir -p ${D}/sbin
	cp ${S}/juci-update ${D}/sbin/
	cp ${S}/scripts/juci-shell ${D}/sbin/
	#luajit
	mkdir -p ${D}/usr/share/lua/5.1
	mv ${D}/usr/share/lua/juci ${D}/usr/share/lua/5.1
	mv ${D}/usr/share/lua/luv ${D}/usr/share/lua/5.1
	mkdir -p ${D}/etc/config
	cp ${S}/juci.config.example ${D}/etc/config/juci
}
