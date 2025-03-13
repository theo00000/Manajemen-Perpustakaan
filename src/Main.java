import java.util.ArrayList;
import java.util.List;

// Interface untuk User
interface AksiUser {
    void login(String inputPassword);
    void logout();
}

// Interface untuk pengelolaan buku oleh Admin
interface AksiBuku {
    void tambahBuku(Buku buku);
    void hapusBuku(String judul);
}

// Interface untuk peminjaman buku oleh Member
interface AksiPeminjaman {
    void pinjamBuku(Buku buku);
    void kembalikanBuku(Buku buku);
}

// Parent class User
abstract class User implements AksiUser {
    protected int id;
    protected String name;
    protected String email;
    protected String password;

    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public void login(String inputPassword) {
        if (this.password.equals(inputPassword)) {
            System.out.println(name + " berhasil login.");
        } else {
            System.out.println("Password salah. Login gagal.");
        }
    }

    @Override
    public void logout() {
        System.out.println(name + " berhasil logout.");
    }
}

// Kelas Admin (Child dari User)
class Admin extends User implements AksiBuku {
    private String jabatan;

    public Admin(int id, String name, String email, String password, String jabatan) {
        super(id, name, email, password);
        this.jabatan = jabatan;
    }

    @Override
    public void tambahBuku(Buku buku) {
        Library.tambahBuku(buku);
        System.out.println("Admin " + name + " menambahkan buku: " + buku.getJudul());
    }

    @Override
    public void hapusBuku(String judul) {
        Library.hapusBuku(judul);
        System.out.println("Admin " + name + " menghapus buku: " + judul);
    }
}

// Kelas Member (Child dari User)
class Member extends User implements AksiPeminjaman {
    private int nomorMember;

    public Member(int id, String name, String email, String password, int nomorMember) {
        super(id, name, email, password);
        this.nomorMember = nomorMember;
    }

    @Override
    public void pinjamBuku(Buku buku) {
        if (buku.isTersedia()) {
            buku.setTersedia(false);
            System.out.println(name + " meminjam buku: " + buku.getJudul());
        } else {
            System.out.println("Buku " + buku.getJudul() + " sedang tidak tersedia.");
        }
    }

    @Override
    public void kembalikanBuku(Buku buku) {
        buku.setTersedia(true);
        System.out.println(name + " mengembalikan buku: " + buku.getJudul());
    }
}

// Kelas Buku
class Buku {
    private String judul;
    private String penulis;
    private String jenisBuku;
    private int tahunTerbit;
    private boolean tersedia;

    public Buku(String judul, String penulis, String jenisBuku, int tahunTerbit) {
        this.judul = judul;
        this.penulis = penulis;
        this.jenisBuku = jenisBuku;
        this.tahunTerbit = tahunTerbit;
        this.tersedia = true; // Default tersedia saat buku dibuat
    }

    public void infoBuku() {
        System.out.println("Judul: " + judul + ", Jenis: " + jenisBuku + ", Penulis: " + penulis +
                ", Tahun: " + tahunTerbit + ", Status: " + (tersedia ? "Tersedia" : "Dipinjam"));
    }

    // Getter dan Setter
    public String getJudul() {
        return judul;
    }

    public boolean isTersedia() {
        return tersedia;
    }

    public void setTersedia(boolean tersedia) {
        this.tersedia = tersedia;
    }
}

// Kelas Perpustakaan untuk mengelola buku dan user
class Library {
    private static List<Buku> daftarBuku = new ArrayList<>();
    private static List<User> daftarUser = new ArrayList<>();

    public static void tambahBuku(Buku buku) {
        daftarBuku.add(buku);
    }

    public static void hapusBuku(String judul) {
        daftarBuku.removeIf(buku -> buku.getJudul().equalsIgnoreCase(judul));
    }

    public static void daftarBuku() {
        System.out.println("\nDaftar Buku di Perpustakaan:");
        for (Buku buku : daftarBuku) {
            buku.infoBuku();
        }
    }

    public static void tambahUser(User user) {
        daftarUser.add(user);
    }

    public static void daftarUser() {
        System.out.println("\nDaftar User di Perpustakaan:");
        for (User user : daftarUser) {
            System.out.println(user.name + " - " + user.email);
        }
    }
}

// Main Program
public class Main {
    public static void main(String[] args) {
        // Membuat Admin dan Member
        Admin admin1 = new Admin(1, "Rina", "rina@email.com", "admin123", "Kepala Perpustakaan");
        Member member1 = new Member(2, "Budi", "budi@email.com", "member123", 101);

        // Tambah User ke Perpustakaan
        Library.tambahUser(admin1);
        Library.tambahUser(member1);

        // Login Admin
        admin1.login("admin123");

        // Admin Menambahkan Buku
        Buku buku1 = new Buku("Pemrograman Java", "James Gosling", "Teknologi", 2020);
        Buku buku2 = new Buku("Belajar Python", "Guido van Rossum", "Teknologi", 2018);

        admin1.tambahBuku(buku1);
        admin1.tambahBuku(buku2);

        // Tampilkan daftar buku
        Library.daftarBuku();

        // Member login dan meminjam buku
        member1.login("member123");
        member1.pinjamBuku(buku1);

        // Cek kembali daftar buku
        Library.daftarBuku();

        // Member mengembalikan buku
        member1.kembalikanBuku(buku1);

        // Cek daftar buku lagi
        Library.daftarBuku();

        // Logout
        admin1.logout();
        member1.logout();
    }
}
