DESCRIPTION = "iconnect is an interconnect package based on stunnel that enables \
connecting multiple devices on ubus using secure sockets."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;;md5=1c6eb38ac0530edc5fb8f704086348e0"

SRC_URI = "file://iconnect.init"
SRC_URI += "file://iconnectd.lua"
SRC_URI += "file://LICENSE"

S = "${WORKDIR}"

do_configure () {
	:
}

do_compile () {
	:
}

do_install () {
	mkdir -p ${D}/etc/stunnel/
#	echo '#dummy' > ${D}/etc/stunnel/stunnel.pem
	mkdir -p ${D}/etc/init.d/
	install -m 722 ${WORKDIR}/iconnect.init ${D}/etc/init.d/iconnect
	mkdir -p ${D}/usr/bin/
	install ${WORKDIR}/iconnectd.lua ${D}/usr/bin/iconnectd
}
