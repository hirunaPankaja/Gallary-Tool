package course_work;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

class ImageNode
{
    String imageName;
    String type;
    Date date;
    ImageNode next;
    Boolean inRecyclebin;
    
    public ImageNode(String imageName,String type,Date date)
    {
        this.imageName = imageName;
        this.type = type;
        this.date = date;
        this.next = null;
        this.inRecyclebin = false;
    }
}

class AlbumNode
{
    String album;
    AlbumNode prev;
    AlbumNode next;
    ImageNode imageHead;
    
    public AlbumNode(String album)
    {
        this.album = album;
        this.prev = null;
        this.next = null;
        this.imageHead = null;
    }
    
    //Add new Media file to album
   public void addImage(String imageName,String type,Date date)
    {
        ImageNode newImageNode = new ImageNode(imageName, type ,date);
        if(imageHead == null)
        {
            imageHead = newImageNode;
            System.out.println(imageName + " Added");
        }
        else
        {
            ImageNode current = imageHead;
            while(current.next != null)
            {
                current = current.next;
            }
            current.next = newImageNode;
            System.out.println(imageName + " Added");
        }
    }

    
    //Recycle bin media delete
    public void deletePermanently(String imageName) {
    ImageNode current = imageHead;
    ImageNode previous = null;
    
    while (current != null) {
        if (current.imageName.equals(imageName) && current.inRecyclebin) {
            if (previous == null) {
                imageHead = current.next;
            } else {
                previous.next = current.next;
            }
            System.out.println(imageName + " deleted permanently.");
            return;
        }
        previous = current;
        current = current.next;
    }
    System.out.println(imageName + " not found");
}
    
    //moving to  the recycle bin
    public void moveToRecycleBin(String imageName) {
        ImageNode current = imageHead;
        while (current != null) {
            if (current.imageName.equals(imageName)) {
                current.inRecyclebin = true;
                System.out.println(imageName + " moved to recycle bin.");
                return;
            }
            current = current.next;
        }
       
    }
    
    //restore media
    public void restore(String imagename)
    {
        ImageNode current = imageHead;
        while(current != null)
        {
            if(current.imageName.equals(imagename) && current.inRecyclebin)
            {
                current.inRecyclebin = false;
                System.out.println("restored from recycle bin");
               
            }
            current = current.next;
   
        }
        

    }
    
    public void printRecycleMedia()
    {
        ImageNode current = imageHead;
        
        while(current !=null )
        {
            if(current.inRecyclebin)
            {
                System.out.println(current.imageName + "(" +current.date+")");
                
            }
            
            current = current.next;
        }
    }
    
   
    

    // Search Image 
    private ImageNode findImage(String imageName) 
    {
        ImageNode current = imageHead;
        while(current != null)
        {
            if(current.imageName.equals(imageName))
            {
                return current;
            }
            current = current.next;
        }
        return null;
    }
    
    //Search Album
    /**public boolean serchAlbum(String albumName)
    {
        AlbumNode current = head;
        while(current != null)
        {
            if(current.album.equals(albumName))
            {
                return true;
            }
            current = current.next;
        }
        return false;
    }*/
    
    
     void printMedia()
    {
        ImageNode current = imageHead;
        
        while(current != null)
        {
            System.out.println(current.imageName+ "." +current.type);
            current = current.next;
        }
    }
}

class DoublyLinkedList
{
    AlbumNode head;
    
    public AlbumNode getAlbumNode(String albumName) 
    {
        AlbumNode current = head;
        while (current != null) 
        {
            if (current.album.equals(albumName)) 
            {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    
    public DoublyLinkedList()
    {
        this.head = null;
    }
    
    public boolean updateAlbum(String albumName, String albumNewName)
    {
        AlbumNode current = head;
        
        while(current != null)
        {
            if(current.album.equals(albumName))
            {
                current.album = albumNewName;
                System.out.println(albumName + " Album Rename to " +albumNewName);
                return true;
            }
            current = current.next;
        }    
        return false;
    }
    
    // Method to insert a node at the beginning of the doubly linked list
    public void insertAtBeginning(String album) 
    {
        AlbumNode newAlbumNode = new AlbumNode(album);
        newAlbumNode.next = head;
        
        if (head != null)
            head.prev = newAlbumNode;
        
        head = newAlbumNode;
    }
    
    //Search Album
    public boolean serchAlbum(String albumName)
    {
        AlbumNode current = head;
        while(current != null)
        {
            if(current.album.equals(albumName))
            {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    // Method to insert a node at the end of the doubly linked list
    public void insertAtEnd(String album) 
    {
        AlbumNode newAlbumNode = new AlbumNode(album);
        AlbumNode last = head;
        
        newAlbumNode.next = null;
        
        if (head == null) {
            newAlbumNode.prev = null;
            head = newAlbumNode;
            return;
        }
        
        while (last.next != null)
            last = last.next;
        
        last.next = newAlbumNode;
        newAlbumNode.prev = last;
    }
    
    // Delete Method
    public void deleteAlbum(String albumName)
    {
        AlbumNode current = head;
        
        while(current != null)
        {   
            // Find Selected Album
            if(current.album.equals(albumName))
            {
                // Delete Head
                if(current == head)
                {
                    head = head.next;
                    
                    if (head != null) 
                    {
                        head.prev = null;
                    }
                }
                
                // Delete last
                else if(current.next == null)
                {
                    current.prev.next = null;
                }
                
                //delete middle
                else
                {
                   current.prev.next = current.next;  
                   current.next.prev = current.prev;
                }
                //System.out.println(albumName + " deleted");
                return;
            }
            else
            {
                current = current.next;  
            }
            
        }
        
        System.out.println(albumName + " Can not find");
     
    }
    
    //print Recyclebin medies
     public void printAllRecycleMedia() {
        AlbumNode current = head;
        while (current != null) {
            current.printRecycleMedia();
            current = current.next;
        }
    }
    
    //restore media in recyclebin
    public void restoreMedia(String mediaName)
    {
        AlbumNode current = head;
        
        while(current != null)
        {
            current.restore(mediaName);
            current = current.next;
        }
        
    }
    
    //delete media in recycle bin
    public void deleteMediaPermanently(String mediaName) {
    AlbumNode current = head;
    while (current != null) {
        current.deletePermanently(mediaName);
        current = current.next;
    }
}

    
    // Display all albums
    public void printList() 
    {
        AlbumNode current = head;
        
        System.out.println("Albums");
        while (current != null) 
        {
            System.out.print(current.album + "         ");
            current = current.next;
        }
        System.out.println();
    }
    
}

public class GalleryTool {
    public static void main(String []args) {
        Scanner scanner = new Scanner(System.in);
        DoublyLinkedList d1 = new DoublyLinkedList();
                
        d1.insertAtBeginning("Camera");
        d1.insertAtEnd("Favorite");
       
        while(true) {
            System.out.println("1.Album");
            System.out.println("2.Video");
            System.out.println("3.Photo");
            System.out.println("4.Add Media");
            System.out.println("5.Recycle Bin");
            System.out.println("92.Exit");
            System.out.println();
            System.out.print("Option    : " );
            
            int number = scanner.nextInt();
            scanner.nextLine();
            System.out.println("------------------------------------------------------------");
            
            switch(number) {
                //Display Albums
                case 1:
                    boolean status = true;
                    while(status) {
                        d1.printList();
                        System.out.println();
                        System.out.println();
                        System.out.println();
                        System.out.println("1.Search Album");
                        System.out.println("2.Create Album");
                        System.out.println("3.Hide Album");
                        System.out.println("92.Back");
                        System.out.println();
                        System.out.print("Option  :  ");
                        int num = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("---------------------------------------------------------------------");
                    
                        switch(num) {
                            case 1:
                                // Search and select album
                                System.out.print("Search Album Name:  ");
                                String albumName = scanner.nextLine();
                                System.out.println();
                                System.out.println();
                                System.out.println("---------------------------------------------------------------------");

                                if(d1.serchAlbum(albumName)) {
                                    System.out.println(albumName);
                                    System.out.println();
                                    AlbumNode foundAlbum = d1.getAlbumNode(albumName);
                                    boolean current = true;
                                    while(current) {
                                        // View Media
                                        foundAlbum.printMedia();
                                        System.out.println();
                                        System.out.println();
                                        System.out.println();
                                        System.out.println("1.Select Image");
                                        System.out.println("2.Rename Album");
                                        System.out.println("3.Delete Album");
                                        System.out.println("4.Hide Album");
                                        System.out.println("5.Add Media");
                                        System.out.println("6.Filter Media");
                                        System.out.println("92.Back");
                                        System.out.println();
                                        System.out.print("Option  :  ");
                                        int option = scanner.nextInt();
                                        scanner.nextLine();
                                        System.out.println("---------------------------------------------------------------------");
                                    
                                        switch(option) {
                                            // Select a Media
                                            case 1:
                                                boolean select = true; 
                                                while(select) {
                                                    System.out.println("1.delete Media ");
                                                    System.out.println("92.Back");
                                                    System.out.println();
                                                    System.out.print("Option  :  ");
                        
                                                    int SelectMedia = scanner.nextInt();
                                                    scanner.nextLine();
                                                    System.out.println("------------------------------------------------------------");
                        
                                                    switch(SelectMedia) {
                                                        case 1:
                                                            System.out.print("Enter Media Name to Delete : ");
                                                            String mediaName = scanner.nextLine();
                    
                                                            // Move the media to recycle bin
                                                            foundAlbum.moveToRecycleBin(mediaName);
                                                            System.out.println(mediaName + " has been moved to the recycle bin.");
                                                            break;
                                                        case 92:
                                                            select = false; 
                                                            break;
                                                    }
                                                }
                                                break;
                                            // Rename Album name
                                            case 2:
                                                System.out.println(albumName + " Album Rename");
                                                System.out.println();
                                                System.out.print("Enter New Name : "  );
                                                System.out.println();
                                                System.out.println();
                                                String albumNewName = scanner.nextLine();
                                                d1.updateAlbum(albumName, albumNewName);
                                                System.out.println();
                                                System.out.println();
                                                System.out.println();
                                                System.out.println("---------------------------------------------------------------------");
                                                break;
                                            // Delete Album
                                            case 3:
                                                System.out.println("Are You Sure Delete This Album..!");
                                                System.out.println("1.Yes");
                                                System.out.println("2.No");
                                                System.out.println();
                                                System.out.print("Option  :  ");
                                                int deletOption = scanner.nextInt();
                                                System.out.println("---------------------------------------------------------------------");
                                                
                                                boolean deleteStatus = true;
                                                while(deleteStatus) {
                                                    if(deletOption == 1) {
                                                        d1.deleteAlbum(albumName);
                                                        System.out.println(albumName +" Album Deleted");
                                                        System.out.println();
                                                        System.out.println();
                                                        System.out.println("---------------------------------------------------------------------");
                                                        deleteStatus = false;
                                                        current = false;
                                                    } else {
                                                        deleteStatus = false;
                                                    }
                                                }
                                                break;
                                            // Hide album 
                                            case 4:
                                                break;
                                            // Filter media
                                            case 6:
                                                int filter = scanner.nextInt();
                                                break;
                                            // Exit out album
                                            case 92:
                                                current = false;
                                                break;
                                        }
                                    }
                                } else {
                                    System.out.println(albumName + " Can Not Found");
                                }
                                break;
                            case 2:
                                // Create new album
                                System.out.print("Enter Name :  ");
                                String name = scanner.nextLine();
                                d1.insertAtEnd(name);
                                System.out.println();
                                System.out.println();
                                break;
                            case 3:
                                // Hide Album View
                                System.out.println("1.View Hide Album");
                                System.out.println("2.Add Hide Album");
                                System.out.println("92.Back");
                                System.out.println();
                                System.out.println();
                                System.out.print("Option  :  ");
                                int hideOption = scanner.nextInt();
                                System.out.println("---------------------------------------------------------------------");
                                
                                boolean hideStatus = true;
                                while(hideStatus) {
                                    switch(hideOption) {
                                        case 1:
                                            // View Hide Album
                                            break;
                                        case 2:
                                            // Add hide album
                                            break;
                                        case 92:
                                            // Go Back
                                            hideStatus = false;
                                            break;
                                    }
                                }
                                break;
                            case 92:
                                // Back main menu
                                status = false;
                                break;
                        } 
                    }
                    break;
                case 2:
                    // Video Category
                    AlbumNode videoAlbum = d1.getAlbumNode("Videos");
                    if (videoAlbum != null) {
                        videoAlbum.printMedia();
                    } 
                    break;
                case 3:
                    // Photo Category
                    AlbumNode photoAlbum = d1.getAlbumNode("Photos");
                    if (photoAlbum != null) {
                        photoAlbum.printMedia();
                    }
                    break;
                case 4:
                    // Add new media to default album
                    System.out.print("Enter Media Name  :  ");
                    String mediaName = scanner.nextLine();
                    System.out.print("Enter File type (png / mp4): ");
                    String type = scanner.nextLine();
                    Date date = new Date();
                    System.out.println();
                    System.out.println();
                    AlbumNode cameraAlbum = d1.getAlbumNode("Camera");
                    if (cameraAlbum != null) {
                        cameraAlbum.addImage(mediaName, type, date);
                        System.out.println("Media added.");
                    } else {
                        System.out.println("Camera album not found.");
                    }
                    System.out.println();
                    System.out.println("------------------------------------------------------------");
                    break;
                case 5:
                    // Recycle Bin
                    boolean recycleBin = true; 
                    while(recycleBin) {
                        System.out.println("Recycle Bin Media:");
                        d1.printAllRecycleMedia();
                        System.out.println();
                        System.out.println();
                        System.out.println("1.Restore Media ");
                        System.out.println("2.Delete Permanetly");
                        System.out.println("92.Back");
                        
                        System.out.println();
                        System.out.print("Option  :  ");
                        int recycle = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("------------------------------------------------------------");
                        
                        switch(recycle) {
                            case 1:
                                System.out.println("Enter media name:");
                                String restoreName = scanner.nextLine();
                                d1.restoreMedia(restoreName);
                                break;
                            case 2:
                                System.out.println("Enter media name:");
                                String deletename = scanner.nextLine();
                                d1.deleteMediaPermanently(deletename);
                                break;
                            case 92:
                                recycleBin = false;
                                break;
                        } 
                    }
                    break;
                case 92:
                    
                    System.out.println("Exiting the application.");
                    System.exit(0);
                    break;
            }
        }          
    }
}