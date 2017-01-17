DESCRIPTION = "Stunnel is a program that allows you to encrypt arbitrary TCP \
connections inside SSL (Secure Sockets Layer) available on both Unix \
and Windows. Stunnel can allow you to secure non-SSL aware daemons and \
protocols (like POP, IMAP, LDAP, etc) by having Stunnel provide the \
encryption, requiring no changes to the daemon's code."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;;md5=866cdc7459d91e092b174388fab8d283"

SRC_URI = "git://github.com/mkschreder/stunnel.git;protocol=git;branch=master"
SRC_URI += "file://stunnel.conf"
SRC_URI += "file://stunnel.init"
SRCREV = "881bfdbb4b13fe285dc52261f9608cadd0cb70a9"
S = "${WORKDIR}/git"
B = "${S}"

inherit autotools

DEPENDS = "openssl tcp-wrappers openssl-native"
RDEPENDS_${PN} = "openssl tcp-wrappers"

EXTRA_OECONF = "--with-random=/dev/urandom \
		--with-threads=fork --with-ssl=${STAGING_LIBDIR}/.. \"

FILES_${PN} += "/usr/bin /usr/lib/stunnel"
FILES_${PN}-dbg += "/usr/bin /usr/lib/stunnel/.debug"

do_compile_prepend () {
	openssl genrsa -out ${WORKDIR}/key.pem 2048
	openssl req -new -x509 -key ${WORKDIR}/key.pem -out ${WORKDIR}/cert.pem -days 1095 -subj '/CN=SE'
}

do_install_append () {
	mkdir -p ${D}/etc/stunnel
	cat ${WORKDIR}/key.pem ${WORKDIR}/cert.pem > ${D}/etc/stunnel/stunnel.pem
	mkdir -p ${D}/etc/config
	cp ${WORKDIR}/stunnel.conf ${D}/etc/config
	mkdir -p ${D}/etc/init.d/
	cp ${WORKDIR}/stunnel.init ${D}/etc/init.d
}

