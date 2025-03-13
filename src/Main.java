class User {
    protected int id;
    protected String name;
    protected String email;

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public void login() {
        System.out.println(name + " berhasil login.");
    }

    public void logout() {
        System.out.println(name + " berhasil logout.");
    }
}

class Admin extends User {
    private String jabatan;

    public Admin(int id, String name, String email, String jabatan) {
        super(id, name, email);
        this.jabatan = jabatan;
    }

    public void tambahBuku(String judul) {
        System.out.println("Admin dengan nama " + name + " menambahkan buku: " + judul);
    }

    public void hapusBuku(String judul) {
        System.out.println("Admin dengan nama " + name + " menghapus buku: " + judul);
    }
}

class Member extends User {
    private int nomorMember;

    public Member(int id, String name, String email, int nomorMember) {
        super(id, name, email);
        this.nomorMember = nomorMember;
    }

    public void pinjamBuku(String judul) {
        System.out.println(name + " telah meminjam buku: " + judul);
    }

    public void kembalikanBuku(String judul) {
        System.out.println(name + " telah mengembalikan buku: " + judul);
    }
}

class Buku {
    private String judul, penulis;
    private String jenisBuku;
    private int tahunTerbit;
    private boolean tersedia;

    public Buku(String judul, String penulis, String jenisBuku, int tahunTerbit) {
        this.judul = judul;
        this.jenisBuku = jenisBuku;
        this.penulis = penulis;
        this.tahunTerbit = tahunTerbit;
        this.tersedia = true;
    }

    public void infoBuku() {
        System.out.println("Judul: " + judul + ", Jenis buku: " + jenisBuku + ", Penulis: " + penulis + ", Tahun: " + tahunTerbit + ", Status: " + (tersedia ? "Tersedia" : "Dipinjam"));
    }
}

public class Main {
    public static void main(String[] args) {
        Admin admin1 = new Admin(1, "Rina", "rinarini@library.com", "Kepala perpustakaan");
        Member member1 = new Member(2, "Budi", "budi@email.com", 12345);

        admin1.login();
        admin1.tambahBuku("Pemrograman Java");
        admin1.logout();

        member1.login();
        member1.pinjamBuku("Pemrograman Java");
        member1.kembalikanBuku("Pemrograman Java");
        member1.logout();
    }
}
