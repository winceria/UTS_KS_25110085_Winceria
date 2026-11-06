
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Barang;

public class BarangDAO {

    // Menyimpan data barang
    public boolean simpan(Barang barang) {
        String sql = "INSERT INTO barang "
                + "(kode_barang, nama_barang, kategori, stok) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = Koneksi.getKoneksi();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, barang.getKodeBarang());
            ps.setString(2, barang.getNamaBarang());
            ps.setString(3, barang.getKategori());
            ps.setInt(4, barang.getStok());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal menyimpan: " + e.getMessage());
            return false;
        }
    }

    // Menampilkan data barang
    public List<Barang> tampilkan() {
        List<Barang> daftar = new ArrayList<>();
        String sql = "SELECT * FROM barang";

        try (Connection conn = Koneksi.getKoneksi();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Barang b = new Barang(
                    rs.getString("kode_barang"),
                    rs.getString("nama_barang"),
                    rs.getString("kategori"),
                    rs.getInt("stok")
                );

                daftar.add(b);
            }

        } catch (SQLException e) {
            System.out.println("Gagal menampilkan: " + e.getMessage());
        }

        return daftar;
    }

    // Mengubah data barang
    public boolean ubah(Barang barang) {
        String sql = "UPDATE barang SET nama_barang=?, "
                + "kategori=?, stok=? WHERE kode_barang=?";

        try (Connection conn = Koneksi.getKoneksi();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, barang.getNamaBarang());
            ps.setString(2, barang.getKategori());
            ps.setInt(3, barang.getStok());
            ps.setString(4, barang.getKodeBarang());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal mengubah: " + e.getMessage());
            return false;
        }
    }

    // Menghapus data barang
    public boolean hapus(String kodeBarang) {
        String sql = "DELETE FROM barang WHERE kode_barang=?";

        try (Connection conn = Koneksi.getKoneksi();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, kodeBarang);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal menghapus: " + e.getMessage());
            return false;
        }
    }
}
