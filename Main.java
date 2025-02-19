import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static int saldo_awal;
    static LocalDate currentDate = LocalDate.now(); // Tanggal berjalan

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
        int jumlah_transaksi_pembeli = 0;
        int saldo_pembeli = saldo_awal;
        double total_pengeluaran; //Total Pengeluaran dari jumlah transaksi yang dihasilkan
        double harga_diskon; //Total harga diskon dari jumlah transaksi yang dihasilkan
        double total_pendapatan; //Total Pendapatan dari transaksi si pembeli sama aja sih sama total pengeluaran si pembeli
        double pembelian_terbesar;

        // Masukkan Stok Awal
        System.out.print("Masukkan stok awal: ");
        stok_awal = scanner.nextInt();

        // Harga Barang
        System.out.print("Masukkan harga barang: ");
        harga_barang = scanner.nextInt();

        // Saldo Awal
        System.out.print("Masukkan saldo awal: ");
        saldo_awal = scanner.nextInt();

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

                //Menu Penjual
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

                        //Fitur Cek Stock apakah tersedia
                        if (perintah_penjual == 1) {
                            System.out.println("Stok saat ini: " + stok_awal);
                        } //Fitur Cek Harga Barang
                        else if (perintah_penjual == 2) {
                            System.out.println("Harga Barang Saat ini: " + harga_barang);
                        } //FItur Jumlah Stok yang Ingin Ditambah
                        else if (perintah_penjual == 3) {
                            System.out.print("Masukkan jumlah stok yang ingin ditambah: ");
                            jumlah_barang_tambah = scanner.nextInt();
                            stok_awal += jumlah_barang_tambah;
                            System.out.println("Jumlah barang saat ini: " + stok_awal);

                            //Fitur Update Harga Barang
                        } else if (perintah_penjual == 4) {
                            System.out.print("Masukkan harga barang yang baru: ");
                            harga_barang = scanner.nextInt();
                            System.out.println("Harga barang saat ini adalah " + harga_barang);

                        } //Fitur Generate Voucher Code untuk Penjual
                        else if (perintah_penjual == 5) {
                           generateCodeVoucher();
                           break;
                        } //Fitur Barang
                        else if (perintah_penjual == 6) {
                            if (jumlah_transaksi_pembeli == 0) {
                                //TO DO
                            }

                        //Fitur Kembali ke Menu Utama (Langsung Break)
                        } else if (perintah_penjual == 8) {
                            break;

                        //Fitur Cek Laporan Pendapatan
                        } else if (perintah_penjual == 7) {
                            laporan_pendapatan_penjualan();;
                        } else 
                        //Fitur Jika angka diluar angka itu sendiri
                        {
                            System.out.println("Pilihan tidak valid, silakan coba lagi.");
                        }
                    }
                    break;

                case 2:

                    //Fitur Menu Pembeli
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

                        //Cek Saldo
                        if (perintah_pembeli == 1) {
                            System.out.println("Saldo saat ini: " + saldo_pembeli);
                            break;

                        //Top Up Saldo
                        } else if (perintah_pembeli == 2) {
                            System.out.print("Masukkan jumlah saldo yang ingin ditambah: ");
                            int saldo_tambah = scanner.nextInt();
                            saldo_pembeli = hasil_tambah_saldo(saldo_pembeli, saldo_tambah);
                            System.out.println("Total saldo saat ini: " + saldo_pembeli);
                            break;

                        //Cek harga Barang
                        } else if (perintah_pembeli == 3) {
                            System.out.println("Harga Barang saat ini: " + harga_barang);
                            break;

                        //Beli Barang
                        } else if (perintah_pembeli == 4) {
                            
                            // User memasukkan jumlah barang yang ingin dibeli
                            System.out.println("Masukkan jumlah barang yang ingin dibeli: ");
                            int jumlah = scanner.nextInt();
                            int totalHarga = jumlah * harga_barang;
                            System.out.println("Harga barang sebelum diskon: " + totalHarga);

                            // Proses Voucher
                            System.out.print("Masukkan kode voucher ('skip' untuk lanjut, 'generate' untuk buat kode baru): ");
                            scanner.nextLine();
                            String request_pembeli = scanner.nextLine();

                            if (request_pembeli.equals("generate")) {
                                String characterCode = generateCodeVoucher();
                                System.out.println(characterCode);

                                int discount_percentage=get_discount(characterCode);
                                double harga_barang_setelah_diskon;
                                jumlah_transaksi_pembeli++;

                                if(discount_percentage<=100){
                                    harga_barang_setelah_diskon=totalHarga-((discount_percentage/100.0)*totalHarga);
                                    double harga_barang_setelah_pajak_three_percent;
                                    harga_barang_setelah_pajak_three_percent=1.03*(harga_barang_setelah_diskon);
                                    System.out.println("Harga Barang Setelah Diskon adalah "+harga_barang_setelah_pajak_three_percent);
                                    total_pendapatan+=harga_barang_setelah_pajak_three_percent;

                                }else{
                                // Menerapkan discount di atas 100 persen
                                    discount_percentage = get_discount_v2(discount_percentage);
                                    harga_barang_setelah_diskon = totalHarga - ((discount_percentage / 100.0) * totalHarga);
                                    double harga_barang_setelah_pajak = harga_barang_setelah_diskon * 1.03;
                                    System.out.println("Harga Barang Setelah Diskon & Pajak: " + harga_barang_setelah_pajak);
                                    total_pendapatan+=harga_barang_setelah_pajak_three_percent;
                                }
                                break;
                                

                                // Jika voucher yang digenerate ada di voucher yang digenerate penjual maka diskon diterapkan
                                // Method Penerapan Rekursif Diskon

                                /*
                                 * Setelah Menerapkan Rekursif Diskon, Kita harus cek Apakah dia Diskonnya diatas 100%
                                 * Jika Diatas 100%, kita terapkan rekursif lagi
                                 * Ketika sudah selesai. Harga Barang baru menjadi sebagai berikut
                                 * Harga Baru (Sebelum Pajak)= (Total Harga Barang - Harga Barang setelah Diskon)
                                 * Harga Baru(Setelah Pajak)= 103/100*Harga Baru (Sebelum Pajak)
                                 */


                            } else if (request_pembeli.equals("skip")) {
                                // Lanjutkan tanpa voucher
                                System.out.println("Melanjutkan tanpa voucher.");
                                jumlah_transaksi_pembeli++;
                                break;

                            } else {
                                System.out.println("Input tidak valid!");
                            }

                            if (totalHarga > saldo_pembeli) {
                                System.out.println("Saldo tidak mencukupi untuk melakukan pembelian!");
                            } else {
                                saldo_pembeli -= totalHarga;
                                jumlah_transaksi_pembeli++;
                                System.out.println("Pembelian berhasil! Total harga setelah diskon: " + totalHarga);
                                System.out.println("Saldo pembeli sekarang: " + saldo_pembeli);
                            }
                        } else if (perintah_pembeli == 5) {
                            String voucher_code=generateCodeVoucher();
                            System.out.println("Kode Voucher yang dihasilkan: "+voucher_code);
                            break;

                        } else if (perintah_pembeli == 6) {
                            // Lacak Barang

                            break;

                        } else if (perintah_pembeli == 7) {
                            laporan_pengeluaran_pembeli();
                            break;

                        } else if (perintah_pembeli == 8) {
                            //Kembali Ke Fitur Utama
                            break;


                        } else {
                            //Mencetak Tidak Valid
                            System.out.println("Pilihan tidak valid.");
                            break;
                        }
                    }
                    break;

                case 3:
                    // Menambahkan satu hari ke tanggal berjalan
                    // Ganti Tanggal
                    maju_satu_hari();
                    break;

                case 4:
                //Fitur Terima Kasih
                    System.out.println("Terima kasih telah menggunakan Burhanpedia!");
                    flag = false;
                    break;

                default:
                //Validity Handling
                    System.out.println("Pilihan tidak valid!");
            }
        }
        scanner.close();
    }

    // Method untuk menambahkan saldo
    public static int hasil_tambah_saldo(int saldo_awal, int saldo_tambah) {
        return saldo_awal + saldo_tambah;
    }

    // Method untuk format tanggal
    public static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy", new Locale("id"));
        return date.format(formatter);
    }

    // Method untuk generate kode karakter acak
    public static String generateCodeVoucher() {
        Random random = new Random();
        String voucher="";
        for(int i=0;i<10;i++){
            char randomCharacter=(char) ('A'+random.nextInt(26));
            int convertedCharacter=((randomCharacter-'A'+10)*i)%10;
            voucher += Integer.toString(convertedCharacter);
        }
        return voucher;
    }

    //Method Rekursif Diskon
    public static int get_discount(String voucher_code) {
        return calculate_discount(voucher_code, 0);
    }
    
    private static int calculate_discount(String code, int index) {
        int length = code.length();
    
        // Base case: jika sudah sampai di tengah atau lebih
        if (index >= length / 2) {
            // Jika panjangnya ganjil, tambahkan digit tengahnya
            return (length % 2 != 0) ? Character.getNumericValue(code.charAt(length / 2)) : 0;
        }
    
        // Ambil digit dari awal dan akhir
        int leftDigit = Character.getNumericValue(code.charAt(index));
        int rightDigit = Character.getNumericValue(code.charAt(length - 1 - index));
    
        // Rekursi ke pasangan berikutnya
        return (leftDigit * rightDigit) + calculate_discount(code, index + 1);
    }

    // Rekursi diskon di atas 100 persen
    public static int get_discount_v2(int discount_percentage) {
        String discountStr = String.valueOf(discount_percentage);
        return calculate_discount_v2_helper(discountStr, 0);
    }

    private static int calculate_discount_v2_helper(String discountStr, int index) {
        if (index >= discountStr.length()) {
            return 1; // Basis: Mengembalikan 1 agar perkalian tidak menjadi 0
        }

        int digit = Character.getNumericValue(discountStr.charAt(index));
        return digit * calculate_discount_v2_helper(discountStr, index + 1);
    }
    
    //Method View Pengeluaran (Pembeli)
    public static void laporan_pengeluaran_pembeli(){
    System.out.println("==Laporan Pengeluaran==");
    System.out.println("Total Pengeluaran: ");
    System.out.println("Jumlah Transaksi: ");
    System.out.println("Rata-Rata Pengeluaran: ");
    System.out.println("Total Diskon Diterima: ");
    System.out.println("Pembelian Terbesar: ");
    }

    //Method View Pendapatan (Penjual)
    public static void laporan_pendapatan_penjualan(){
        System.out.println("==Laporan Pendapatan Penjualan==");
        System.out.println("Total Pendapatan: ");
        System.out.println("Jumlah Transaksi: ");
        System.out.println("Rata-Rata Pendapatan: ");
        System.out.println("Total Diskon Diberikan: ");
        System.out.println("Pendapatan Terbesar: ");
    }

    
    public static void maju_satu_hari(){
        currentDate = currentDate.plusDays(1);
        System.out.println("Tanggal berganti ke: " + formatDate(currentDate));
    }
}