import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static int saldo_awal;
    static LocalDate currentDate = LocalDate.now(); // Tanggal berjalan
    static boolean isBarangDikirim = false; // Status pengiriman barang
    static LocalDate tanggalPengiriman = null; // Tanggal pengiriman barang
    static double totalPengeluaran = 0;
    static int jumlahTransaksi = 0;
    static double totalDiskonDiterima = 0;
    static double pembelianTerbesar = 0;

    public static void main(String[] args) {
        System.out.println("=============================================================");
        System.out.println("============== Selamat datang di Burhanpedia! ===============");
        System.out.println("=============================================================");

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Deklarasi Variabel
        int stok_awal, harga_barang;
        boolean flag = true;
        int perintah_menu, perintah_penjual, perintah_pembeli;
        int jumlah_barang_tambah;
        int jumlah_voucher_yang_sudah_dibuat_penjual = 0;
        int jumlah_transaksi_pembeli = 0;
        String semua_voucher_penjual = "";
        int saldo_pembeli;

        // Masukkan Stok Awal
        System.out.print("Masukkan stok awal: ");
        stok_awal = scanner.nextInt();

        // Harga Barang
        System.out.print("Masukkan harga barang: ");
        harga_barang = scanner.nextInt();

        // Saldo Awal
        System.out.print("Masukkan saldo awal: ");
        saldo_pembeli = scanner.nextInt();

        // Fitur Menu Utama
        while (flag) {
            System.out.println("\n===== MENU UTAMA =====");
            System.out.println("1. Penjual");
            System.out.println("2. Pembeli");
            System.out.println("3. Hari Selanjutnya (" + formatDate(currentDate) + ")");
            System.out.println("4. Keluar");
            System.out.print("Perintah: ");
            perintah_menu = scanner.nextInt();

            switch (perintah_menu) {
                case 1:
                    while (true) {
                        System.out.println("\n===== MENU PENJUAL =====");
                        System.out.println("1. Cek Stok");
                        System.out.println("2. Cek Harga Barang");
                        System.out.println("3. Tambah Stok");
                        System.out.println("4. Ubah Harga Barang");
                        System.out.println("5. Generate Voucher");
                        System.out.println("6. Kirim Barang");
                        System.out.println("7. Lihat Laporan Pendapatan");
                        System.out.println("8. Kembali ke menu utama");
                        System.out.println("9. Periksa Jumlah Voucher Yang sudah Di Generate");
                        System.out.print("Perintah: ");
                        perintah_penjual = scanner.nextInt();

                        if (perintah_penjual == 1) {
                            System.out.println("Stok saat ini: " + stok_awal);
                        } 
                        else if (perintah_penjual == 2) {
                            System.out.println("Harga Barang Saat ini: " + harga_barang);
                        } 
                        else if (perintah_penjual == 3) {
                            System.out.print("Masukkan jumlah stok yang ingin ditambah: ");
                            jumlah_barang_tambah = scanner.nextInt();
                            stok_awal += jumlah_barang_tambah;
                            System.out.println("Jumlah barang saat ini: " + stok_awal);
                        } 
                        else if (perintah_penjual == 4) {
                            System.out.print("Masukkan harga barang yang baru: ");
                            harga_barang = scanner.nextInt();
                            System.out.println("Harga barang saat ini adalah " + harga_barang);
                        } 
                        else if (perintah_penjual == 5) {
                            String characterCode = generateCharacterCode();
                            System.out.println("Generated Character Code: " + characterCode);
                            String numericCode = convertToNumericCode(characterCode);
                            System.out.println("Generated Numeric Code: " + numericCode);
                            semua_voucher_penjual += numericCode + "\n";
                            jumlah_voucher_yang_sudah_dibuat_penjual++;
                        } 
                        else if (perintah_penjual == 6) {
                            if (jumlah_transaksi_pembeli == 0) {
                                System.out.println("Tidak ada transaksi yang dapat dikirim.");
                            } else {
                                System.out.println("Masukkan nomor transaksi yang ingin dikirim (1-" + jumlah_transaksi_pembeli + "): ");
                                int nomorTransaksi = scanner.nextInt();
                                
                                if (nomorTransaksi > 0 && nomorTransaksi <= jumlah_transaksi_pembeli) {
                                    System.out.println("Transaksi #" + nomorTransaksi + " sedang diproses.");
                                    // Kirim barang jika stok mencukupi
                                    if (stok_awal > 0) {
                                        stok_awal--;
                                        isBarangDikirim = true;
                                        tanggalPengiriman = currentDate;
                                        System.out.println("Barang telah dikirim!");
                                        System.out.println("Sisa stok: " + stok_awal);
                                    } else {
                                        System.out.println("Stok barang tidak mencukupi untuk pengiriman!");
                                    }
                                } else {
                                    System.out.println("Nomor transaksi tidak valid.");
                                }
                            }
                        } 
                        else if (perintah_penjual == 9) {
                            System.out.println("Jumlah Voucher yang Sudah Dibuat: " + jumlah_voucher_yang_sudah_dibuat_penjual);
                            if (jumlah_voucher_yang_sudah_dibuat_penjual == 0) {
                                System.out.println("Belum ada voucher yang dibuat.");
                            } else {
                                System.out.println("Daftar Voucher:");
                                Scanner voucherScanner = new Scanner(semua_voucher_penjual);
                                while (voucherScanner.hasNextLine()) {
                                    System.out.println(voucherScanner.nextLine());
                                }
                            }
                        } 
                        else if (perintah_penjual == 8) {
                            break;
                        } 
                        else {
                            System.out.println("Pilihan tidak valid, silakan coba lagi.");
                        }
                    }
                    break;

                case 2:
                    while (true) {
                        System.out.println("\n===== MENU PEMBELI =====");
                        System.out.println("1. Cek Saldo");
                        System.out.println("2. Top Up Saldo");
                        System.out.println("3. Cek Harga Barang");
                        System.out.println("4. Beli Barang");
                        System.out.println("5. Generate Voucher");
                        System.out.println("6. Lacak Barang");
                        System.out.println("7. Lihat Laporan Pengeluaran");
                        System.out.println("8. Kembali ke Menu Utama");
                        System.out.print("Pilih menu: ");
                        perintah_pembeli = scanner.nextInt();

                        if (perintah_pembeli == 1) {
                            System.out.println("Saldo saat ini: " + saldo_pembeli);
                        } 
                        else if (perintah_pembeli == 2) {
                            System.out.print("Masukkan jumlah saldo yang ingin ditambah: ");
                            int saldo_tambah = scanner.nextInt();
                            saldo_pembeli = hasil_tambah_saldo(saldo_pembeli, saldo_tambah);
                            System.out.println("Total saldo saat ini: " + saldo_pembeli);
                        } 
                        else if (perintah_pembeli == 3) {
                            System.out.println("Harga Barang saat ini: " + harga_barang);
                        } 
                        else if (perintah_pembeli == 4) {
                            // User memasukkan jumlah barang yang ingin dibeli
                            System.out.println("Masukkan jumlah barang yang ingin dibeli: ");
                            int jumlah = scanner.nextInt();
                            int totalHarga = jumlah * harga_barang;
                            System.out.println("Harga barang sebelum diskon: " + totalHarga);

                            // Proses Voucher
                            System.out.print("Masukkan kode voucher ('skip' untuk lanjut, 'generate' untuk buat kode baru): ");
                            scanner.nextLine(); // Consume the leftover newline character
                            String request_pembeli = scanner.nextLine();

                            if (request_pembeli.equals("generate")) {
                                String characterCode = generateCharacterCode();
                                System.out.println("Generated Character Code: " + characterCode);
                                String numericCode = convertToNumericCode(characterCode);
                                System.out.println("Generated Numeric Code: " + numericCode);

                                // Jika voucher yang digenerate ada di voucher yang digenerate penjual maka diskon diterapkan
                                if (semua_voucher_penjual.contains(numericCode)) {
                                    int diskon = 100; // Misalnya, diskon 100
                                    System.out.println("Voucher valid! Diskon diterapkan: " + diskon);
                                    totalHarga -= diskon;
                                    if (totalHarga < 0) totalHarga = 0; // Pastikan harga tidak kurang dari 0
                                } else {
                                    System.out.println("Voucher tidak valid!");
                                }
                            } else if (request_pembeli.equals("skip")) {
                                // Lanjutkan tanpa voucher
                                System.out.println("Melanjutkan tanpa voucher.");
                            } else {
                                System.out.println("Input tidak valid!");
                            }

                            if (totalHarga > saldo_pembeli) {
                                System.out.println("Saldo tidak mencukupi untuk melakukan pembelian!");
                            } else {
                                saldo_pembeli -= totalHarga;
                                jumlah_transaksi_pembeli++;
                                totalPengeluaran += totalHarga;
                                if (totalHarga > pembelianTerbesar) {
                                    pembelianTerbesar = totalHarga;
                                }
                                System.out.println("Transaksi berhasil! Sisa saldo: " + saldo_pembeli);
                            }
                        }
                        else if (perintah_pembeli == 6) {
                            // Implementasi status pengiriman barang
                            if (isBarangDikirim) {
                                if (tanggalPengiriman.isBefore(currentDate)) {
                                    System.out.println("Status pengiriman: Sending");
                                } else {
                                    System.out.println("Tidak ada barang yang sedang dikirim.");
                                }
                            } else {
                                System.out.println("Tidak ada barang yang sedang dikirim.");
                            }
                        }
                        else if (perintah_pembeli == 7) {
                            // Menampilkan laporan pengeluaran
                            if (jumlah_transaksi_pembeli == 0) {
                                System.out.println("Tidak ada transaksi untuk laporan.");
                            } else {
                                double rataRataPengeluaran = totalPengeluaran / jumlah_transaksi_pembeli;
                                System.out.println("===== LAPORAN PENGELUARAN =====");
                                System.out.println("Total Pengeluaran: " + totalPengeluaran);
                                System.out.println("Jumlah Transaksi: " + jumlah_transaksi_pembeli);
                                System.out.println("Rata-rata Pengeluaran: " + rataRataPengeluaran);
                                System.out.println("Total Diskon Diterima: " + totalDiskonDiterima);
                                System.out.println("Pembelian Terbesar: " + pembelianTerbesar);
                            }
                        }
                        else if (perintah_pembeli == 8) {
                            break;
                        } else {
                            System.out.println("Pilihan tidak valid, silakan coba lagi.");
                        }
                    }
                    break;

                case 3:
                    currentDate = currentDate.plusDays(1); // Hari selanjutnya
                    System.out.println("Hari telah berganti: " + formatDate(currentDate));
                    break;

                case 4:
                    System.out.println("Terima kasih telah menggunakan layanan kami!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    // Metode untuk menformat tanggal menjadi string
    public static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.forLanguageTag("id-ID"));
        return date.format(formatter);
    }

    public static String generateCharacterCode() {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    // Method untuk mengubah kode karakter menjadi kode numerik
    public static String convertToNumericCode(String characterCode) {
        StringBuilder numericCode = new StringBuilder();
        for (char character : characterCode.toCharArray()) {
            numericCode.append(getNumericValue(character));
        }
        return numericCode.toString();
    }

    // Method untuk mendapatkan nilai numerik dari karakter
    public static int getNumericValue(char character) {
        return character - 'A'+10;
    }

    // Menambah saldo pembeli
    public static int hasil_tambah_saldo(int saldo, int saldo_tambah) {
        return saldo + saldo_tambah;
    }
}