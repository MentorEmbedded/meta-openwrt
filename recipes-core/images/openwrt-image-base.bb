SUMMARY = "openwrt minimal image"

IMAGE_INSTALL = "packagegroup-openwrt-boot rpcd uci iwinfo uhttpd2 luci luci2 luajit dnsmasq netifd strace"
IMAGE_FEATURES += ""

LICENSE = "MIT"

inherit core-image

fix_image () {
	#not needed the only things to be done is to set localstatedir = /tmp 
	#rm -rf ${IMAGE_ROOTFS}/var
	if false
	then
		ln -sf ../init.d/log ${IMAGE_ROOTFS}/etc/rc.d/K89log
		ln -sf ../init.d/boot ${IMAGE_ROOTFS}/etc/rc.d/K98boot
		ln -sf ../init.d/umount ${IMAGE_ROOTFS}/etc/rc.d/K99umount
		ln -sf ../init.d/sysfixtime ${IMAGE_ROOTFS}/etc/rc.d/S00sysfixtime
		ln -sf ../init.d/boot ${IMAGE_ROOTFS}/etc/rc.d/S10boot
		ln -sf ../init.d/system ${IMAGE_ROOTFS}/etc/rc.d/S10system
		ln -sf ../init.d/sysctl ${IMAGE_ROOTFS}/etc/rc.d/S11sysctl
		ln -sf ../init.d/log ${IMAGE_ROOTFS}/etc/rc.d/S12log
		ln -sf ../init.d/rpcd ${IMAGE_ROOTFS}/etc/rc.d/S12rpcd
		ln -sf ../init.d/done ${IMAGE_ROOTFS}/etc/rc.d/S95done
	fi
	pushd ${IMAGE_ROOTFS} 
	for script in ./etc/init.d/*
	do 
		grep '#!/bin/sh /etc/rc.common' $script >/dev/null || continue
		IPKG_INSTROOT=${IMAGE_ROOTFS} $(which bash) ./etc/rc.common $script enable
	done || true
	popd

	#FIXME: put the below to sysvinit.bb instead
	echo "::sysinit:/etc/init.d/rcS S boot" > ${IMAGE_ROOTFS}/etc/inittab
	echo "::shutdown:/etc/init.d/rcS K shutdown" >> ${IMAGE_ROOTFS}/etc/inittab
	echo "tts/0::askfirst:/bin/ash --login" >> ${IMAGE_ROOTFS}/etc/inittab

	tmp="${SERIAL_CONSOLES}"
	for i in $tmp
	do
		j=`echo ${i} | sed s/\;/\ /g`
		label=`echo ${i} | sed -e 's/^.*;tty//' -e 's/;.*//'`
		echo "tty${label}::askfirst:/bin/ash --login" >> ${IMAGE_ROOTFS}/etc/inittab
	done
	#echo "ttyAMA0::askfirst:/bin/ash --login" >> ${IMAGE_ROOTFS}/etc/inittab

	echo "tty1::askfirst:/bin/ash --login" >> ${IMAGE_ROOTFS}/etc/inittab
}

IMAGE_PREPROCESS_COMMAND_append = "fix_image"
