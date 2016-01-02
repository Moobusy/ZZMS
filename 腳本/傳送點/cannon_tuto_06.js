function enter(pi) {
	pi.lockUI(true);
	pi.disableOthers(true);
    pi.sendDirectionStatus(4, 1096003);
	pi.sendDirectionStatus(3, 4);
	pi.openNpc(1096003);
}