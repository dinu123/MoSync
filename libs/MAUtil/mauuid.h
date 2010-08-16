/* Copyright (C) 2009 Mobile Sorcery AB

This program is free software; you can redistribute it and/or modify it under
the terms of the GNU General Public License, version 2, as published by
the Free Software Foundation.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
for more details.

You should have received a copy of the GNU General Public License
along with this program; see the file COPYING.  If not, write to the Free
Software Foundation, 59 Temple Place - Suite 330, Boston, MA
02111-1307, USA.
*/

/** \file MAUUID.h
* \brief Bluetooth UUID definitions.
*/

#ifndef MAUUID_H
#define MAUUID_H

/**
* Defines a MAUUID that follows the Bluetooth standard of UUIDs.
* The lower 96 bits are constant.
*/
#define DEFINE_BTMAUUID(name, uuid16) static const MAUUID name = {{ uuid16, 0x00001000, 0x80000080, 0x5F9B34FB }}

DEFINE_BTMAUUID(Bluetooth_Base_MAUUID, 0);

DEFINE_BTMAUUID(L2CAP_PROTOCOL_MAUUID, 0x0100);

DEFINE_BTMAUUID(SDP_PROTOCOL_MAUUID, 0x0001);
DEFINE_BTMAUUID(UDP_PROTOCOL_MAUUID, 0x0002);
DEFINE_BTMAUUID(RFCOMM_PROTOCOL_MAUUID, 0x0003);
DEFINE_BTMAUUID(TCP_PROTOCOL_MAUUID, 0x0004);
DEFINE_BTMAUUID(TCSBIN_PROTOCOL_MAUUID, 0x0005);
DEFINE_BTMAUUID(TCSAT_PROTOCOL_MAUUID, 0x0006);
DEFINE_BTMAUUID(OBEX_PROTOCOL_MAUUID, 0x0008);
DEFINE_BTMAUUID(IP_PROTOCOL_MAUUID, 0x0009);
DEFINE_BTMAUUID(FTP_PROTOCOL_MAUUID, 0x000A);
DEFINE_BTMAUUID(HTTP_PROTOCOL_MAUUID, 0x000C);
DEFINE_BTMAUUID(WSP_PROTOCOL_MAUUID, 0x000E);
DEFINE_BTMAUUID(BNEP_PROTOCOL_MAUUID, 0x000F);
DEFINE_BTMAUUID(UPNP_PROTOCOL_MAUUID, 0x0010);
DEFINE_BTMAUUID(HID_PROTOCOL_MAUUID, 0x0011);
DEFINE_BTMAUUID(HCCC_PROTOCOL_MAUUID, 0x0012);
DEFINE_BTMAUUID(HCDC_PROTOCOL_MAUUID, 0x0014);
DEFINE_BTMAUUID(HCN_PROTOCOL_MAUUID, 0x0016);
DEFINE_BTMAUUID(AVCTP_PROTOCOL_MAUUID, 0x0017);
DEFINE_BTMAUUID(AVDTP_PROTOCOL_MAUUID, 0x0019);
DEFINE_BTMAUUID(CMPT_PROTOCOL_MAUUID, 0x001B);
DEFINE_BTMAUUID(UDI_C_PLANE_PROTOCOL_MAUUID, 0x001D);

DEFINE_BTMAUUID(ServiceDiscoveryServer_Service_MAUUID, 0x1000);
DEFINE_BTMAUUID(BrowseGroupDescriptor_Service_MAUUID, 0x1001);
DEFINE_BTMAUUID(PublicBrowseGroup_Service_MAUUID, 0x1002);
DEFINE_BTMAUUID(SerialPort_Service_MAUUID, 0x1101);
DEFINE_BTMAUUID(LANAccessUsingPPP_Service_MAUUID, 0x1102);
DEFINE_BTMAUUID(DialupNetworking_Service_MAUUID, 0x1103);
DEFINE_BTMAUUID(IrMCSync_Service_MAUUID, 0x1104);
DEFINE_BTMAUUID(OBEXObjectPush_Service_MAUUID, 0x1105);
DEFINE_BTMAUUID(OBEXFileTransfer_Service_MAUUID, 0x1106);
DEFINE_BTMAUUID(IrMcSyncCommand_Service_MAUUID, 0x1107);
DEFINE_BTMAUUID(Headset_Service_MAUUID, 0x1108);
DEFINE_BTMAUUID(Cordless_Service_MAUUID, 0x1109);
DEFINE_BTMAUUID(AudioSource_Service_MAUUID, 0x110A);
DEFINE_BTMAUUID(AudioSinkSource_Service_MAUUID, 0x110B);
DEFINE_BTMAUUID(AVRemoteControlTarget_Service_MAUUID, 0x110C);
DEFINE_BTMAUUID(AdvancedAudioDistribution_Service_MAUUID, 0x110D);
DEFINE_BTMAUUID(AVRemoteControl_Service_MAUUID, 0x110E);
DEFINE_BTMAUUID(VideoConferencing_Service_MAUUID, 0x110F);
DEFINE_BTMAUUID(Intercom_Service_MAUUID, 0x1110);
DEFINE_BTMAUUID(Fax_Service_MAUUID, 0x1111);
DEFINE_BTMAUUID(HeadsetAudioGateway_Service_MAUUID, 0x1112);
DEFINE_BTMAUUID(WAP_Service_MAUUID, 0x1113);
DEFINE_BTMAUUID(WAPClient_Service_MAUUID, 0x1114);
DEFINE_BTMAUUID(PANU_Service_MAUUID, 0x1115);
DEFINE_BTMAUUID(NAP_Service_MAUUID, 0x1116);
DEFINE_BTMAUUID(GN_Service_MAUUID, 0x1117);
DEFINE_BTMAUUID(DirectPrinting_Service_MAUUID, 0x1118);
DEFINE_BTMAUUID(ReferencePrinting_Service_MAUUID, 0x1119);
DEFINE_BTMAUUID(Imaging_Service_MAUUID, 0x111A);
DEFINE_BTMAUUID(ImagingResponder_Service_MAUUID, 0x111B);
DEFINE_BTMAUUID(ImagingAutomaticArchive_Service_MAUUID, 0x111C);
DEFINE_BTMAUUID(ImagingReferenceObjects_Service_MAUUID, 0x111D);
DEFINE_BTMAUUID(Handsfree_Service_MAUUID, 0x111E);
DEFINE_BTMAUUID(HandsfreeAudioGateway_Service_MAUUID, 0x111F);
DEFINE_BTMAUUID(DirectPrintingReferenceObjects_Service_MAUUID, 0x1120);
DEFINE_BTMAUUID(ReflectsUI_Service_MAUUID, 0x1121);
DEFINE_BTMAUUID(BasicPrinting_Service_MAUUID, 0x1122);
DEFINE_BTMAUUID(PrintingStatus_Service_MAUUID, 0x1123);
DEFINE_BTMAUUID(HumanInterfaceDevice_Service_MAUUID, 0x1124);
DEFINE_BTMAUUID(HardcopyCableReplacement_Service_MAUUID, 0x1125);
DEFINE_BTMAUUID(HCRPrint_Service_MAUUID, 0x1126);
DEFINE_BTMAUUID(HCRScan_Service_MAUUID, 0x1127);
DEFINE_BTMAUUID(CommonISDNAccess_Service_MAUUID, 0x1128);
DEFINE_BTMAUUID(VideoConferencingGW_Service_MAUUID, 0x1129);
DEFINE_BTMAUUID(UDIMT_Service_MAUUID, 0x112A);
DEFINE_BTMAUUID(UDITA_Service_MAUUID, 0x112B);
DEFINE_BTMAUUID(AudioVideo_Service_MAUUID, 0x112C);

DEFINE_BTMAUUID(PnPInformation_Service_MAUUID, 0x1200);
DEFINE_BTMAUUID(GenericNetworking_Service_MAUUID, 0x1201);
DEFINE_BTMAUUID(GenericFileTransfer_Service_MAUUID, 0x1202);
DEFINE_BTMAUUID(GenericAudio_Service_MAUUID, 0x1203);
DEFINE_BTMAUUID(GenericTelephony_Service_MAUUID, 0x1204);

#endif	//MAUUID_H
