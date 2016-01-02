
function enter(pi) { 
	pi.lockUI(false);
	pi.disableOthers(false);
	if (pi.isQuestFinished(2568) && pi.getQuestStatus(2570) == 0) {
	    pi.showInstruction("Where am I? My head hurts...", 150, 5);
	}
}  