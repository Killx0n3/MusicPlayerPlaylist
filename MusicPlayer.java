
import java.util.*;

class musicNode {
    String track;  // The name of the track
    int played= 0; // The number of times played
    int shuffleTag= 0; // For shuffling
    musicNode next;
    
    public musicNode() {		// Here's how we construct an empty list.
        next = null;
    }
    public musicNode(String t) {
        track = t; next = null;
    }
    public musicNode(String t, musicNode ptr) {
        track = t; next = ptr;
    }
    
     public boolean LTTrack(musicNode x) {   // Compares tracks according to alphabetical order on strings
    	 if (this.track.compareTo(x.track)<=0) return true;
    	 else return false;
     }
     
     public boolean LTPlayed(musicNode x) {   // Compares according to the played field.
    	 if (this.played <= x.played) return true;
    	 else return false;
     }
     
     public boolean LTTag(musicNode x) {   // Compares according to the shuffle tag.
    	 if (this.shuffleTag <= x.shuffleTag) return true;
    	 else return false;
     }
}; 

// This class represents a playlist;
// We assume that each track appears at most once in the playlist

public class MusicPlayer {
	protected musicNode head = null; // Pointer to the top of the list.
	int length=0;   // the number of nodes in the list.
    boolean debug= false;
    
    public  MusicPlayer() {
    }
    public void setToNull() {
        head = null;
    }
    public boolean isEmpty() {
        return head == null;
    }
    public String firstTrack() {
        return head.track;
    }
    
    public int firstPlaycount() {
        return head.played;
    }
    
    public int firstTag() {
        return head.shuffleTag;
    }
       
    public musicNode head() {
        return head;
    }
    public void printAll() 
    {   for (musicNode tmp = head; tmp != null; tmp = tmp.next)
            System.out.print(tmp.track.toString());
            System.out.print('\n');
    }
    
    void playTrack(String name){  // Simulates playing a track: searches for the name and increments its played field
    	musicNode tmp = head;
    	for (; tmp != null && (tmp.track.compareTo(name)!=0); tmp = tmp.next) {
    		
    	}
            if (tmp!= null) tmp.played= tmp.played+1;   	
    }
    
    void insertTrack(String name) { // Inserts a new track at the top of the list.
    	musicNode temp= new musicNode(name, head);
    	head= temp;
        length++;
   }
    
    void sortTrack() { 
    	// Sorts (ascending) the list according to the name of the track
    	if (head!=null){
    		musicNode newNode = mergeSort(head, 1);
        	head = newNode;
    		}
    	
    	}
    
    void sortPlayed() {  // This is optional but might be useful for shuffling.
    	// Sorts (ascending) the list according to the number of times played
    	if (head!=null){
    		musicNode newNode = mergeSort(head, 3);
        	head = newNode;
    		}
    	}
       
    int countItem(String item) {
    	// Returns the number of times that item occurs in the current list
    	int count = 0;
    	for (musicNode tmp = head; tmp != null; tmp = tmp.next){
    		if (tmp.track.equals(item))
    			count++;
    	}
    	return count;
    }
     
    musicNode checkMembership(String _track) {
    	// If the given _track is present in the current list (i.e. the node whose "track" field
    	// is equal to _track), returns the address of that node;
    	// otherwise returns null. 
    	for (musicNode tmp = head; tmp != null; tmp = tmp.next){
    		if (tmp.track.equals(_track))
    			return tmp;
    	}
    	
    	return null;
    }
    
    void shuffle() {
    	// Randomly shuffles the list
    	Random rand = new Random();
		int  randomNo = 0;
		
		for (musicNode tmp = head; tmp != null; tmp = tmp.next){
			randomNo = rand.nextInt(size(head)) + 1;
			tmp.shuffleTag=randomNo;
		}
		
		if (head!=null){
    		musicNode newNode = mergeSort(head, 2);
        	head = newNode;
    		}
		
    }
    
    void smartShuffle () {
    	// Shuffles the list, whilst respecting the special condition on played
    	sortPlayed();
    }
    
    void recommended(String[] historyList) {
    	if (head!=null){
    		musicNode tmp2 = head;
    		int flag =0;
        	for (musicNode tmp = head; tmp != null; tmp = tmp.next){
        		if (flag == 1){
        			tmp2.next=null;
        			head=tmp;
        		}	
        		
        		flag =0;
        		tmp.shuffleTag=0;
        		for (int i=0; i<historyList.length; i++){
            		if (tmp.track==historyList[i])
            			tmp.shuffleTag++;
            	}
        		if (tmp.shuffleTag==0 &&tmp!=head)
        			tmp2.next=tmp.next;
        		else if (tmp.shuffleTag==0 &&tmp==head)
        			flag = 1;
        		tmp2=tmp;
    		}
        	
        	musicNode newNode = mergeSort(head, 4);
        	head = newNode;
    	}
    	
    }
    
    void moveFirstNode(MusicPlayer fromList, MusicPlayer toList) {
    	// Removes the top node of fromList and puts it onto (the top of) toList.
    	// If fromList is empty, it does nothing.
    	if (fromList.isEmpty()==false){
    		toList.head=fromList.head;
            toList.length++;
            fromList.head=fromList.head.next;
    	}
    	
    }
    
    
    // ************* MERGE LIST STARTS *************
    
    public musicNode mergeSort(musicNode a, int type) {
		musicNode previous = a;
		int half = size(a) / 2;
		if (a.next == null)
			return a;
		while (half-1>0) {
			previous = previous.next;
			half--;
		}
		musicNode current = previous.next;
		previous.next = null;
		previous = a;
		musicNode trackNodeOne = mergeSort(previous, type);
		musicNode trackNodeTwo = mergeSort(current, type);
		return merge(trackNodeOne, trackNodeTwo, type);
	}
    
    public musicNode merge(musicNode a, musicNode b, int type) {
    	musicNode output = null;
		if (a == null)
			return b;
		if (b == null)
			return a;
		if (type==1){
			if (a.LTTrack(b)==false) {
				output = b;
				output.next = merge(a, b.next, type);
			} else {
				output = a;
				output.next = merge(a.next, b, type);
			}
		}
		else if (type==2){
			if (a.LTTag(b) ==false) {
				output = b;
				output.next = merge(a, b.next, type);
			} else {
				output = a;
				output.next = merge(a.next, b, type);
			}
		}
		else if (type==3){
			if (a.LTPlayed(b) ==false) {
				output = b;
				output.next = merge(a, b.next, type);
			} else {
				output = a;
				output.next = merge(a.next, b, type);
			}
		}
		else if (type==4){
			if (a.LTTag(b) ==true) {
				output = b;
				output.next = merge(a, b.next, type);
			} else {
				output = a;
				output.next = merge(a.next, b, type);
			}
		}
		
		return output;
	}
	
	public int size(musicNode a) {
		int count = 0;
		musicNode tempNode = a;
		while (tempNode != null) {
			count++;
			tempNode = tempNode.next;
		}
		return count;
	}

	// ************* MERGE LIST ENDS *************

}


