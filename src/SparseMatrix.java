/** @author Chad Wishner
 * 	COP3530 Section: 1079 Data Structures and Algorithms
 */
public class SparseMatrix implements SparseInterface {	
	Node head;
		
	int size;
	
	/**	default constructor Sparse Matrix
	 * 	default size to 5	
	 * 	includes Node head and int size
	 * 	
	 */
	public SparseMatrix(){
		head = null;
		
		//default size 5
		size = 5;
	}
	
	/** Node class:
	 * 	includes int row, int col, int data, Node next
	 * 	default constructor
	 * 	specified value constructor
	 */
	public class Node {
		//node data
		int data;
		int row;
		int col;
		Node next;
		
		//constructor for specified values
		public Node(int da, int r, int c){
			data = da;
			row = r;
			col = c;		
		}
		
		//default constructor
		public Node(){
			data = 0;
			row = 0;
			col = 0;
		}
	}

	/** Clear the matrix of all entries (make all entries 0)
	 * 	O(1)
     */
    public void clear(){
    	//setting head to null clears reference to the rest of the nodes, garbage collector will collect
    	this.head = null;
    }

    /** Sets maximum size of the matrix.  Number of rows. It should also clear the matrix (make all elements 0)
     * 	@param int size
     * 	O(1)
     */
    public void setSize(int size){
    	//set size to specified value
    	this.size = size;
    	
    	//clears matrix
    	this.clear();
    }

    /** Adds an element to the row and column passed as arguments (overwrites if element is already present at that position). Throws an error if row/column 
     * 	combination is out of bounds. Adds values in order of row then column (ie. row 0 col 0, row 0 col 1, row 0 col 2, row 1 col 0, row 1 col 1, row 1 col 2...) 
     * 	@param int row, int col, int data
     * 	O(n)
     */
    public void addElement(int row, int col, int data){
    	//add in order
    	
    	//check if out of bound combination
    	if (row > size || col > size){
    		System.err.println("Out of Bound row/col combination");
    	} else {
    		
    		//if data is 0, remove the element at row/col and return
    		if (data == 0){
    			this.removeElement(row, col);
    			return;
    		}
    		
    		//create new node to add
    		Node add = new Node(data, row, col);
    		
    		//if head is empty, make the head the new node
    		if (head == null){
    			head = add;
    		} else {
    			
    			//create pointer nodes to increment through
    			Node cur = head;
    			Node prev = null;
    			
    			//find the correct row
    			while (cur != null && cur.row < row){
        			prev = cur;
            		cur = cur.next;
        		}
    			
    			//special if row doesn't exist yet, and it is after the rows that do exist, place it at the end
    			if (cur == null){
    				prev.next = add;
    				return;
    			}
  
    			
    			//special case if row doesn't exist and it is between 2 rows, place it between the 2 rows    			if (cur.row > row){
    			if (cur.row > row && prev != null){
    				prev.next = add;
    				add.next = cur;
    				return;
    			} else {
    				
    				//find the correct col
    				while (cur != null && cur.row == row && cur.col < col){
    					prev = cur;
                		cur = cur.next;
        			} 
    				       			
    				//special case if the 1st row doesn't exist, thus the prev pointer node has not been changed, and the new node needs to be placed at head
        			if (prev == null){
        				head = add;
        				add.next = cur;
        				return;
        			}
        			
    				//if final col isn't there, and it is at the end of the list, place the node at the tail
    				if (cur == null){
        				prev.next = add;
        				return;
        			}
    				
    				//if the node already exists, overwrite that node
    				if (cur.row == row && cur.col == col){
        				cur.data = data;
        				
        			//normal case of simply adding the new node in the correct space
        			} else {
        				prev.next = add;
        				add.next = cur;
        			}
    			}
    		}
    	}
    }
    
    /** Remove (make 0) the element at the specified row and column. Throws an error if row/column combination is out of bounds.
     * 	@param int row, int col (of the desired element)
     * 	O(n)
     */
    public void removeElement(int row, int col){
    	
    	//check if out of bound combination
    	if (row > size || col > size){
    		System.err.println("Out of Bound row/col combination");
    	} else {
    		
    		//create cur and prev node pointers
    		Node cur = head;
    		Node prev = new Node();
    		
    		//find the right row/col combination
    		while (cur.row != row || cur.col != col){
    			prev = cur;
    			cur = cur.next;
    		}
    		
    		//skip over cur
    		prev.next = cur.next;
    		
    		//special case if cur is head, set the head pointer
    		if (cur == head){
    			head = head.next;
    		}
    		
    		//delete cur
    		cur = null;
    	}
    
    }

    /** Returns the element at the specified row and column. Throws an error if row/column combination is out of bounds.
     * 	@return int data
     * 	@param int row, int col (of the desired element)
     * 	O(n)
     */
    public int getElement(int row, int col){
    	
    	//check if out of bound combination
    	if (row > size || col > size){
    		System.err.println("Out of Bound row/col combination");
    	} else {
    		
    		//create cur node pointer
    		Node cur = head;
    		
    		//find the right row/col combination
    		while (cur.row != row || cur.col != col){
    			cur = cur.next;
    		}

    		//return data
    		return cur.data;
    	}
    	
    	//if you don't find it, return 0 (the index is 0 in the matrix)
    	return 0;
    }

    /** Calculate the determinate recursively
     * 	@return  int the determinant of the matrix calculated recursively
     *	O(n!)
     */
    public int determinant(){
    	    	
    	//store sum
    	int sum = 0;
    	
    	//create pointer node
    	Node cur = head;
    	
    	//base case
    	if (head != null && this.size == 1){
    		return head.data;
    	} else if (head == null){
    		return 0;
    	}
    	
    	//run through the first row
    	while (cur != null && cur.row == 0){
    		
    		//algorithm with recursive call
    		sum += (int)Math.pow(-1, cur.row + cur.col) * cur.data * minor(cur.row, cur.col).determinant();
    		cur = cur.next;
    	}
    	
    	//return sum determinate
    	return sum;
    }

    /** Returns a new matrix which is the minor of the original (See project description for minor definition).
     * 	@return SparceInterface (linkedlist)
     * 	@param int row, int col (to be removed)
     * 	O(n)
     */
    public SparseInterface minor(int row, int col){
    	
    	//create new linked list to store minor
    	SparseMatrix minor = new SparseMatrix();
    	
    	//minor size is just the normal size - 1
    	minor.setSize(size-1);
    	
    	//cur node
    	Node cur = head;
    	
    	//iterate through matrix
    	while (cur != null){
    		
    		//find elements that are not included in the crossed out row/col
    		if (cur.row != row && cur.col != col){
    			
    			//if upper left of the cross, just add
    			if (cur.row < row && cur.col < col){
    				minor.addElement(cur.row, cur.col, cur.data);
    				
    			//if upper right, add with col - 1
    			} else if (cur.row < row && cur.col > col){
    				minor.addElement(cur.row, cur.col-1, cur.data);
    			
    			//if bottom left, add with row - 1
    			} else if (cur.row > row && cur.col < col){
    				minor.addElement(cur.row-1, cur.col, cur.data);
    				
    			//if bottom right, add with row - 1 and col - 1
    			} else if (cur.row > row && cur.col > col){
    				minor.addElement(cur.row-1, cur.col-1, cur.data);
    			}
    		}
    		
    		//increment cur
    		cur = cur.next;
    	}

    	//return minor Sparse Matrix (linked list)
    	return minor;
    }

    /** Return the nonzero elements of your sparse matrix as a string. The String is k lines, where k is the number of nonzero elements. 
     * 	Each line should be in the format "row column data" where row and column are the "coordinate" of the data and all are separated by spaces. An empty matrix should 
     * 	return an empty string. The print is be from left to right and from top to bottom (like reading a book) i.e. the matrix
     * 	@return String
     * 	O(n)
     */
    public String toString(){
    	
    	//create empty string and cur node
    	String matrix = "";
		Node cur = head;

		//increment and add the correct information to the string
    	while (cur != null){
    		matrix += cur.row + " " + cur.col + " " + cur.data + "\n";
    		cur = cur.next;
    	}
    	
    	//return string
    	return matrix;
    }

    /** Returns the size of the matrix.
     * 	@return int size
     * 	O(1)
     */
    public int getSize(){
    	
    	//return size
    	return this.size;
    }
}