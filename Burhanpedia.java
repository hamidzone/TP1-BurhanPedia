import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;


public class Burhanpedia {
    static int stok_awal;
    static int harga_barang;
    static int saldo_awal;
    static double total_pengeluaran=0.0;
    static int transaksi=0;
    static double total_pendapatan=0.0;
    static double avg_pendapatan=0.0;
    static double penjualan_terbesar=0.0;
    static double pembelian_terbesar=0.0;
    static boolean flag=true;
    static double total_discount=0.0;
    static int hari_terlewat=0;
    static double total_diskon=0.0;
    static LocalDate tanggal = LocalDate.now();
    
        public static void main(String[] args) {
            System.out.println("        _____         ");
            System.out.println("        |  __ \\       ");
            System.out.println("        _ _        ");
            System.out.println("        | (_)       ");
            System.out.println("        | |_) |_   _ _ __| |__   __ _ _ __ | |__) |__  __| |_  __ _  ");
            System.out.println("        |  _ <| | | | '__| '_ \\ / _` | '_ \\|  ___/ _ \\/ _` | |/ _` | ");
            System.out.println("        | |_) | |_| | |  | | | | (_| | | | | |  |  __/ (_| | | (_| | ");
            System.out.println("        |____/ \\__,_|_|  |_| |_|\\__,_|_| |_|_|   \\___|\\__,_|_|\\__,_| ");
            System.out.println("        ============================================================= ");
            System.out.println("        ============== Selamat datang di Burhanpedia! =============== ");
            System.out.println("        ============================================================= ");
    
            //Deklarasi Variabel
            int tambah_stok;
            int harga_terbaru;
            int transaksi=0;
            int diskon;
            int tambah_saldo;
            int menu_choice;
            int penjual_choice;
            int pembeli_choice;
            int jumlah_item_yang_mau_dibeli;
            double total_harga_item_request_pembeli;
    
            //Siapkan Apa yang Mau diimport
            Scanner scanner=new Scanner(System.in);
            Random random=new Random();
    
            //Masukkan Stok Awal
            System.out.print("Masukkan stok awal:");
            stok_awal=scanner.nextInt();
    
            //Masukkan harga barang
            System.out.print("Masukkan harga barang:");
            harga_barang=scanner.nextInt();
    
            //Masukkan saldo_awal
            System.out.print("Masukkan saldo awal:");
            saldo_awal=scanner.nextInt();
    
            //Fitur Menu Utama
            while(flag){
                System.out.println("Pilih menu ");
                System.out.println("1. Penjual ");
                System.out.println("2. Pembeli ");
                System.out.println("3. Hari Selanjutnya ");
                System.out.println("4. Keluar ");
                System.out.print("Perintah: ");
                menu_choice=scanner.nextInt();
                switch(menu_choice){
                    case 1:
                        // Fitur Menu Penjual
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
                            System.out.print("Perintah: ");
                            penjual_choice = scanner.nextInt();
                
                            if(penjual_choice == 1){
                                // Implement action for cek stok
                                cek_stok();
                                break;
                            }
                            
                            else if(penjual_choice == 2){
                                // Implement action for cek harga barang
                                cek_harga_barang();
                                break;
                            }
                            
                            else if(penjual_choice == 3){
                                // Implement action for tambah stok
                                System.out.print("Masukkan jumlah stok yang ingin ditambah: ");
                                tambah_stok=scanner.nextInt();
                                penambahan_stok(tambah_stok);
                                break;
                            }
                            
                            else if(penjual_choice == 4){
                                System.out.println("Masukkan harga barang yang baru: ");
                                harga_terbaru=scanner.nextInt();
                                ubah_harga_barang(harga_terbaru);
                                break;
                            }
    
                            else if(penjual_choice == 5){
                                // Implement action for generate voucher
                                String generated_voucher=generatecodeVoucher();
                                System.out.println("Voucher berhasil dibuat: "+generated_voucher);
                                break;
                            }
                            
                            else if(penjual_choice == 6){
                                kirim_barang(transaksi);
                                break;
                            }
                            
                            else if(penjual_choice == 7){
                                laporan_penjualan();
                                penjualan_terbesar=pembelian_terbesar;
                                break;
                            }
                            
                            else if(penjual_choice == 8){
                                break; // Kembali ke menu utama
                            }
                            
                            else{
                                System.out.println("Pilihan anda tidak valid!");
                            }
                        }
                        break; 
                        
                    case 2:
                        // Fitur Menu Pembeli
                        while(true){
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
                            pembeli_choice = scanner.nextInt();
                
                            if(pembeli_choice == 1){
                                // Implement action for cek saldo
                                cek_saldo();
                                break;
                            }
    
                            else if(pembeli_choice == 2){
                                // Implement action for top up saldo
                                System.out.println("Masukkan jumlah saldo yang ingin ditambah:");
                                tambah_saldo=scanner.nextInt();
                                top_up_saldo(tambah_saldo);
                                System.out.println("Saldo berhasil ditambah! Saldo saat ini:"+top_up_saldo(tambah_saldo));
                                break;
                            }
    
                            else if(pembeli_choice == 3){
                                // Implement action for cek harga barang
                                cek_harga_barang();
                                break;
                            }

                            else if (pembeli_choice == 4) {
                                // Implement action for beli barang
                                System.out.println("Masukkan jumlah barang yang ingin dibeli: ");
                                jumlah_item_yang_mau_dibeli = scanner.nextInt();
                                
                                // Cek dulu kira-kira pembeli bisa tidak membeli barang
                                total_harga_item_request_pembeli = harga_barang * jumlah_item_yang_mau_dibeli;
                                
                                if (jumlah_item_yang_mau_dibeli <= stok_awal) {
                                    if (saldo_awal >= total_harga_item_request_pembeli) {
                                        System.out.println("Masukkan kode voucher");
                                        System.out.println("Jika tidak ada, ketik 'skip'");
                                        System.out.println("Jika ingin buat, ketik 'generate'");
                                        System.out.println("========================================");
                                        System.out.print("Kode: ");
                                        String buyer_code_option = scanner.next();
                                        
                                        if (buyer_code_option.equals("generate")) {
                                            String generated_voucher = generatecodeVoucher();
                                            System.out.println("Voucher berhasil dibuat: " + generated_voucher);
                                            diskon = calculated_discount(generated_voucher);
                                            transaksi++;
                                            stok_awal -= jumlah_item_yang_mau_dibeli;
                                            
                                            if (diskon <= 100) {
                                                total_harga_item_request_pembeli -= ((double) diskon / 100) * total_harga_item_request_pembeli;
                                                total_harga_item_request_pembeli += (0.03 * total_harga_item_request_pembeli);
                                                double total_harga_diskon=((double) diskon /100)*total_harga_item_request_pembeli;
                                                if (total_harga_item_request_pembeli>pembelian_terbesar){
                                                    pembelian_terbesar=total_harga_item_request_pembeli;
                                                }
                                                System.out.println("Voucher berhasil digunakan. Harga setelah diskon: " + total_harga_item_request_pembeli);
                                            } else {
                                                int actual_discount = calculated_discount_above_100(String.valueOf(diskon));
                                                total_harga_item_request_pembeli -= ((actual_discount / 100.0) * total_harga_item_request_pembeli);
                                                total_harga_item_request_pembeli += (0.03 * total_harga_item_request_pembeli);
                                                System.out.println("Voucher berhasil digunakan. Harga setelah diskon: " + total_harga_item_request_pembeli);
                                            }
                                            
                                            total_pengeluaran += total_harga_item_request_pembeli;
                                            if (total_harga_item_request_pembeli > pembelian_terbesar) {
                                                pembelian_terbesar = total_harga_item_request_pembeli;
                                            }
                                        } else if (buyer_code_option.equals("skip")) {
                                            System.out.println("Pembelian sukses! Saldo saat ini:");
                                            stok_awal -= jumlah_item_yang_mau_dibeli;
                                            transaksi++;
                                        } else {
                                            System.out.println("Input tidak valid");
                                        }
                                    } else {
                                        System.out.println("Saldo anda tidak mencukupi");
                                    }
                                } else {
                                    System.out.println("Barang tidak bisa dibeli");
                                }

                                pembelian_terbesar=penjualan_terbesar;
                            }                            

                        else if(pembeli_choice == 5){
                            // Implement action for generate voucher
                            String generated_voucher=generatecodeVoucher();
                            System.out.println("Voucher berhasil dibuat: "+generated_voucher);
                            break;
                        }

                        else if(pembeli_choice == 6){
                            lacak_barang(transaksi);
                            break;
                        }

                        else if(pembeli_choice == 7){
                            laporan_pengeluaran();
                            break;
                        }
                    
                        else if(pembeli_choice == 8){
                            break; // Kembali ke Menu Utama
                        }

                        else{
                            System.out.println("Pilihan anda tidak valid");
                        }
                    }
                    break; // End of case 2
                    
                case 3:
                    // Fitur Hari Selanjutnya
                    hariSelanjutnya();
                    break;
            
                case 4:
                    // Fitur Keluar dari BurhanPedia
                    System.out.println("Terima kasih telah menggunakan Burhanpedia!");
                    flag = false;
                    break;
            
                default:
                    System.out.println("Pilihan tidak valid!");
                    break;
            }            
        }
    }
    //Method-Method
    public static void cek_stok(){
        System.out.println("=============================");
        System.out.println("Stok saat ini: "+stok_awal);
        System.out.println("============================");
    }
    public static void cek_saldo(){
        System.out.println("=============================");
        System.out.println("Saldo anda saat ini "+saldo_awal);
        System.out.println("============================");
    }

    public static void cek_harga_barang(){
        System.out.println("=============================");
        System.out.println("Harga barang saat ini "+harga_barang);
        System.out.println("============================");
    }

    public static void penambahan_stok(int tambah_stok){
        stok_awal=stok_awal+tambah_stok;
        if(tambah_stok<0){
            System.out.println("Jumlah tidak boleh negatif atau nol");
        }else{
            System.out.println("Stok berhasil ditambah! Stok saat ini: "+stok_awal);
        }
    }

    public static void ubah_harga_barang(int harga_terbaru){
        harga_barang=harga_terbaru;
        System.out.println("Harga barang diperbarui:"+harga_barang);
    }

    public static int top_up_saldo(int tambah_saldo){
        saldo_awal=saldo_awal+tambah_saldo;
        return saldo_awal;
    }

    public static String generatecodeVoucher(){
        Random random=new Random();
        String code_voucher="";
        for(int i=0;i<10;i++){
            char random_character=(char) ('A'+random.nextInt(26));
            int converted_character=((random_character-'A'+10)*i)%10;
            code_voucher+=Integer.toString(converted_character);
        }
        return code_voucher;
    }

    public static int calculated_discount(String generated_voucher){
        int length=generated_voucher.length();
        if(length==0){
            return 0;
        }else if(length==1){
            return Character.getNumericValue(generated_voucher.charAt(length));
        }else{
            int left_digit=Character.getNumericValue(generated_voucher.charAt(0));
            int right_digit=Character.getNumericValue(generated_voucher.charAt(length-1));

            //Menghitung perkalian
            int product=left_digit*right_digit;

            //Panggilan rekursif
            return product+ calculated_discount(generated_voucher.substring(1, length - 1));
        }
    }

    public static int calculated_discount_above_100(String diskon){
        int length=diskon.length();
        int left_digit=Character.getNumericValue(diskon.charAt(0));
        int right_digit=Character.getNumericValue(diskon.charAt(length-1));

        //Menghitung perkalian
        int product=left_digit*right_digit;
        
        //Panggilan rekursif
        return product+calculated_discount_above_100(diskon.substring(1,length-1));
    }


    public static void lacak_barang(int transaksi){
        if(transaksi<1){
            System.out.println("Tidak ada barang yang bisa dikirim.");
        }else{
            System.out.println("Barang sedang dalam perjalanan.");
        }
    }

    public static void laporan_penjualan(){
        System.out.println("Total Pendapatan: "+total_pengeluaran);
        System.out.println("Jumlah Transaksi: "+transaksi);
        System.out.println("Rata-Rata Pendapatan: "+(total_pendapatan/transaksi));
        System.out.println("Total diskon yang diterima: "+total_diskon);
        System.out.println("Pembelian Terbesar:"+penjualan_terbesar);
    }

    public static void kirim_barang(int transaksi){
        if(transaksi<1){
            System.out.println("Tidak ada barang yang bisa dikirim.");
        }else{
            System.out.println("Barang sedang dalam perjalanan.");
        }
    }

    public static void laporan_pengeluaran(){
        System.out.println("Total Pengeluaran: "+total_pengeluaran);
        System.out.println("Jumlah Transaksi: "+transaksi);
        System.out.println("Rata-Rata Pendapatan: "+(total_pengeluaran/transaksi));
        System.out.println("Total diskon yang diterima: "+total_diskon);
        System.out.println("Pengeluaran Terbesar:"+pembelian_terbesar);
    }

    public static void hariSelanjutnya() {
        tanggal = tanggal.plusDays(1);
        System.out.println("Tanggal sekarang: " + tanggal);
    }
}