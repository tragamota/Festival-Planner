//package ComponentManager;
//
//import Data.*;
//import javax.swing.*;
//import javax.swing.event.ListDataListener;
//
//public class ComboBox extends JComboBox
//{
//    private ComboBoxModel comboBoxModel;
//
//    private TimeTable timeTable;
////    private final int TARGET_DAYS = 0;
////    private final int TARGET_STAGES = 1;
////    private final int TARGET_PERFORMANCES = 2;
////    private final int TARGET_ARTISTS = 3;
////    private final int TARGET_HOSTS = 4;
//
//    private enum targetComponent {DAYS, STAGES, PERFORMANCES, ARTISTS, HOSTS};
//
//    public ComboBox(TimeTable timeTable)
//    {
//        super();
//
//        switch(targetComponent)
//        {
//            case DAYS: return; break;
//            case STAGES: return; break;
//            case PERFORMANCES; return; break;
//            case ARTISTS; return; break;
//            case HOSTS; return; break;
//        }
//
//        final int targetComponentFinal = targetComponent;
//
//        setModel(comboBoxModel = new ComboBoxModel() {
//            @Override
//            public void setSelectedItem(Object anItem)
//            {
//                if(targetComponentFinal == TARGET_DAYS)
//                {
//                    timeTable.getDays().get(getSelectedIndex());
//                }
//                else if(targetComponentFinal == TARGET_STAGES)
//                {
//                    timeTable.getStages().get(getSelectedIndex());
//                }
//                else if(targetComponentFinal == TARGET_PERFORMANCES)
//                {
//                    timeTable.getPerformances().get(getSelectedIndex());
//                }
//                else if(targetComponentFinal == TARGET_ARTISTS)
//                {
//                    timeTable.getArtists().get(getSelectedIndex());
//                }
//                else if(targetComponentFinal == TARGET_HOSTS)
//                {
//                    timeTable.getHosts().get(getSelectedIndex());
//                }
//            }
//
//            @Override
//            public Object getSelectedItem() {
//            {
//                if(targetComponentFinal == TARGET_DAYS)
//                {
//                    timeTable.getDays().get(getSelectedIndex());
//                }
//                else if(targetComponentFinal == TARGET_STAGES)
//                {
//                    timeTable.getStages().get(getSelectedIndex());
//                }
//                else if(targetComponentFinal == TARGET_PERFORMANCES)
//                {
//                    timeTable.getPerformances().get(getSelectedIndex());
//                }
//                else if(targetComponentFinal == TARGET_ARTISTS)
//                {
//                    timeTable.getArtists().get(getSelectedIndex());
//                }
//                else if(targetComponentFinal == TARGET_HOSTS)
//                {
//                    timeTable.getHosts().get(getSelectedIndex());
//                }
//            }
//
//            @Override
//            public int getSize() {
//                return 0;
//            }
//
//            @Override
//            public Object getElementAt(int index) {
//                return null;
//            }
//
//            @Override
//            public void addListDataListener(ListDataListener l) {
//
//            }
//
//            @Override
//            public void removeListDataListener(ListDataListener l) {
//
//            }
//        });
//    }
//}
