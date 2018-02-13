public class SparseMatrix implements SparseInterface {
//testing git
	
	Node head;
//	Node tail;
		
	int size;
		
	public SparseMatrix(){
		head = null;
//		tail = null;
		size = 5;
	}
		
	public class Node {
		int data;
		int row;
		int col;
		Node next;
			
		public Node(int da, int r, int c){
			data = da;
			row = r;
			col = c;		
		}
		public Node(){
			data = 0;
			row = 0;
			col = 0;
		}
	}

	/* Should clear the matrix of all entries (make all entries 0)
     */
    public void clear(){
    	this.head = null;
    }

    /* Sets maximum size of the matrix.  Number of rows. It should also clear the matrix (make all elements 0)
     */
    public void setSize(int size){
    	this.size = size;
    	this.clear();
    }

    /* Adds an element to the row and column passed as arguments (overwrites if element is already present at that position). 
     * Throws an error if row/column combination is out of bounds.
     */
    public void addElement(int row, int col, int data){
    	//add in order
    	
    	if (row > size || col > size){
    		System.err.println("Out of Bound row/col combination");
    	} else {
    		if (data == 0){
    			this.removeElement(row, col);
    			return;
    		}
    		
    		Node add = new Node(data, row, col);
    		
    		if (head == null){
    			head = add;
//    			tail = add;
    		} else {
    			Node cur = head;
    			Node prev = null;
    			
    			while (cur != null && cur.row < row){
        			prev = cur;
            		cur = cur.next;
        		}
    			
    			if (cur == null){
    				prev.next = add;
    				return;
    			}
    			if (prev == null){
    				head = add;
    				add.next = cur;
    				return;
    			}
    			if (cur.row > row){
    				prev.next = add;
    				add.next = cur;
    			} else {
    				while (cur != null && cur.col < col){
    					prev = cur;
                		cur = cur.next;
        			} 
    				
    				if (cur == null){
        				prev.next = add;
        				return;
        			}
    				
    				if (cur.col == col){
        				cur.data = data;
        			} else {
        				prev.next = add;
        				add.next = cur;
        			}
    			}

//    			tail.next = add;
//    			tail = add;
    		}
    	}
    }
    

    /* Remove (make 0) the element at the specified row and column.
     * Throws an error if row/column combination is out of bounds.
     */
    public void removeElement(int row, int col){
    	if (row > size || col > size){
    		System.err.println("Out of Bound row/col combination");
    	} else {
    		Node cur = head;
    		Node prev = new Node();
    		while (cur.row != row || cur.col != col){
    			prev = cur;
    			cur = cur.next;
    		}
    		
    		prev.next = cur.next;
    		
    		if (cur == head){
    			head = head.next;
    		}
//   		if (cur == tail){
//   			tail = prev;
//    		}
    		
    		cur = null;
    	}
    
    }

    /* Return the element at the specified row and column
     * Throws an error if row/column combination is out of bounds.
     */
    public int getElement(int row, int col){
    	if (row > size || col > size){
    		System.err.println("Out of Bound row/col combination");
    	} else {
    		Node temp = head;
    		while (temp.row != row || temp.col != col){
    			temp = temp.next;
    		}

    		return temp.data;
    	}
    	
    	return 0;
    }

    /* Returns the determinant of the matrix calculated recursively (Use the formula provided in the project description).
     */
    public int determinant(){
    	int sum = 0;
    	Node cur = head;
    	
    	while (cur.row == 0){
    		sum += (int)Math.pow(-1, cur.row + cur.col) * cur.data * minor(cur.row, cur.col).determinant();
    		cur = cur.next;
    	}
    	
    	 return sum;
    }

    /* Returns a new matrix which is the minor of the original (See project description for minor definition).
     */
    public SparseInterface minor(int row, int col){
    	SparseMatrix minor = new SparseMatrix();
    	minor.setSize(size-1);
    	
    	Node temp = head;
    	
    	while (temp != null){
    		if (temp.row != row && temp.col != col){
    			if (temp.row < row && temp.col < col){
    				minor.addElement(temp.row, temp.col, temp.data);
    			} else if (temp.row < row && temp.col > col){
    				minor.addElement(temp.row, temp.col-1, temp.data);
    			} else if (temp.row > row && temp.col < col){
    				minor.addElement(temp.row-1, temp.col, temp.data);
    			} else if (temp.row > row && temp.col > col){
    				minor.addElement(temp.row-1, temp.col-1, temp.data);
    			}
    		}
    		temp = temp.next;
    	}

    	return minor;
    }

    /* Should return the nonzero elements of your sparse matrix as a string.
     * The String should be k lines, where k is the number of nonzero elements.
     * Each line should be in the format "row column data" where row and column are the "coordinate" of the data and all are separated by spaces.
     * An empty matrix should return an empty string.
     * The print should be from left to right and from top to bottom (like reading a book) i.e. the matrix

                                                     3 0 1
                                                     0 2 0
                                                     0 0 4

                                                 Should print as:
                                                     0 0 3
                                                     0 2 1
                                                     1 1 2
                                                     2 2 4

     */
    public String toString(){
    	String matrix = "";
		Node cur = head;

    	while (cur != null){
    		matrix += cur.row + " " + cur.col + " " + cur.data + "\n";
    		cur = cur.next;
    	}
    	System.out.println(matrix);
    	return matrix;
    }

    /* Should return the size of the matrix.
     */
    public int getSize(){
    	return this.size;
    }
}
